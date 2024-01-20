package org.bayisehat.rdb2fhir.core.valueservice;

import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadruple;
import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;
import org.bayisehat.rdb2fhir.core.valueservice.jython.PyInterpreter;

import java.util.*;

/**
 * This class manage mapping defined in quadruple
 * The values will be preprocessed (if needed), split into lazy and non-lazy path, and assigned them to model instances
 * Non-lazy paths will first  be assigned with values before lazy paths
 * */
public class ValueManager {

    private final PyInterpreter pyInterpreter;

    private final Allocator allocator;

    private final KeyValueWrapperFactory keyValueWrapperFactory;

    private final InstancePoolFactory instancePoolFactory;

    public ValueManager(PyInterpreter pyInterpreter, Allocator allocator,
                        InstancePoolFactory instancePoolFactory, KeyValueWrapperFactory keyValueWrapperFactory) {
        this.pyInterpreter = pyInterpreter;
        this.allocator = allocator;
        this.instancePoolFactory = instancePoolFactory;
        this.keyValueWrapperFactory = keyValueWrapperFactory;
    }

    /**
     * Transform the given quadruple and result values into model instances
     * */
    public InstancePool transform(ArrayList<ResultQuadruple> resultQuadrupleList) throws Exception {
        InstancePool instancePool = instancePoolFactory.create();
        HashMap<Quadruple, ArrayList<HashSet<KeyValueWrapper>>> lazyList = new LinkedHashMap<>();

        /* For every result quadruple */
        for(ResultQuadruple resultQuadruple: resultQuadrupleList){
            Quadruple quadruple = resultQuadruple.quadruple();

            /* To store instance of resource for every row */
            ArrayList<HashSet<KeyValueWrapper>> instanceRowLazyWrapper = new ArrayList<>();

            /* for every row in table */
            for (HashMap rowResult : resultQuadruple.result()) {

                /* Flatten and apply function if exists
                 * Container hold list of every path to multiple column (for flatten)
                 * HashMap<target, HashMap<target, value>> containerForFlatten */
                HashMap<String, HashMap<String, String>> containerForFlatten = new HashMap<>();
                for (Map.Entry<String, HashSet<String>> entry: quadruple.getColumnToPathList().entrySet()) {
                    String column = entry.getKey();
                    for (String path : entry.getValue()) {
                        HashMap<String, String> columnToValue = containerForFlatten.containsKey(path) ?
                                containerForFlatten.get(path) : new HashMap<>();

                        if (! quadruple.getColumnNameList().contains(column)) {
                            throw new Exception("Unknown column: " + column);
                        }

                        Object v = rowResult.get(column);
                        String val = (v instanceof String) ? (String) v :
                                (v== null) ? null : v.toString();
                        columnToValue.put(column, val);
                        containerForFlatten.put(path, columnToValue);
                    }
                }
                //  flattenContainer hold element list for every element to 1 column (flatten)/function applied
                HashMap<String, String> flattenContainer = applyFunction(quadruple, containerForFlatten);

                /* Split flattenContainer into lazy and non-lazy element
                 * lazy will be filled after all non-lazy element in current result quadruple have been filled */
                HashMap<String, HashMap<String, String>> split = split(quadruple.getLazyPathList(), flattenContainer);
                HashMap<String, String> rowLazy = split.get("lazy");
                HashMap<String, String> rowNonLazy = split.get("non-lazy");

                if (!rowNonLazy.isEmpty()) {
                    KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, rowNonLazy);
                    allocator.fill(instancePool, keyValueWrapper);
                }

                HashSet<KeyValueWrapper> instanceRowLazy = new HashSet<>();
                if (!rowLazy.isEmpty()) {
                    KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, rowLazy);
                    instanceRowLazy.add(keyValueWrapper);
                    instanceRowLazyWrapper.add(instanceRowLazy);
                }

            }

            /* save all row contain column and value to be seeded later after all
             * non-lazy in all workspace have been seeded*/
            if (!instanceRowLazyWrapper.isEmpty()) {
                lazyList.put(quadruple, instanceRowLazyWrapper);
            }


        }

        /* Process deferred Item
         * for every workspace */
        for(Map.Entry<Quadruple, ArrayList<HashSet<KeyValueWrapper>>> entry: lazyList.entrySet()){
            ArrayList<HashSet<KeyValueWrapper>> rows = entry.getValue();

            /* For every instance with row in a workspace/table*/
            for (HashSet<KeyValueWrapper> row : rows) {
                /* For every row i*/
                for (KeyValueWrapper keyValueWrapper : row) {
                    allocator.fill(instancePool, keyValueWrapper);
                }
            }
        }

        return instancePool;

    }


    /**
     * Split paths and its corresponding values by two,
     * Lazy path for paths that contain non-indexed element,
     * Non-lazy path for paths that contain indexed element or regular element
     * */
    private HashMap<String, HashMap<String, String>> split(HashSet<String> lazyTargetList, HashMap<String, String> flattenContainer) {
        HashMap<String, String> rowNonLazy = new HashMap<>();
        HashMap<String, String> rowLazy = new HashMap<>();

        for (Map.Entry<String, String> entry1: flattenContainer.entrySet()) {
            String element = entry1.getKey();
            String value = entry1.getValue();

            //Some lazy path may be a terminal and at the same time not terminal
            // for example Patient.name[0].given and Patient.name[0].given.extension[0].url
            if(lazyTargetList.contains(element) || lazyTargetList.stream().anyMatch(s-> element.startsWith(s + "."))){
                rowLazy.put(element, value);
            }else{
                rowNonLazy.put(element, value);
            }
        }

        HashMap<String, HashMap<String, String>> returnVal = new HashMap<>();
        returnVal.put("lazy", rowLazy);
        returnVal.put("non-lazy",rowNonLazy);
        return returnVal;
    }


    /**
     * Perform preprocessing (if needed)
     * */
    private HashMap<String, String> applyFunction(Quadruple quadruple, HashMap<String, HashMap<String, String>> container) throws Exception {
        HashMap<String, String> flattenContainer = new HashMap<>();

        //execute function
        for (Map.Entry<String, String> entry : quadruple.getPathToFunction().entrySet()) {
            String target = entry.getKey();
            String functionValue = entry.getValue();

            if (!container.containsKey(target)) { //Only function without parameter
                String result = pyInterpreter.execute(functionValue);
                flattenContainer.put(target, result);
                //cleaning up
                pyInterpreter.cleanVariableColumnValue(new HashMap<>());
            } else {
                pyInterpreter.initVariableColumnValue(container.get(target));
                String result = pyInterpreter.execute(functionValue);
                flattenContainer.put(target, result);

                //cleaning up column variable
                pyInterpreter.cleanVariableColumnValue(container.get(target));
            }
        }

        for (String element : container.keySet()) {
            //skip element that have been function applied
            if (flattenContainer.containsKey(element)) {
                continue;
            }

            HashMap<String, String> columnToValueList = container.get(element);
            if (columnToValueList.size() > 1) {
                throw new Exception("Multiple column should have been function applied");
            }
            flattenContainer.put(element, new ArrayList<>(columnToValueList.values()).get(0));
        }

        return flattenContainer;

    }


}
