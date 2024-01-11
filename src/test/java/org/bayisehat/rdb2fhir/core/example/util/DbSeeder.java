package org.bayisehat.rdb2fhir.core.example.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class DbSeeder {

    private SqlSessionFactory sqlSessionFactory;

    public DbSeeder(SqlSessionFactory sqlSessionFactory) throws Exception {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void seed(BaseParser baseParser) {
            createTable(baseParser, sqlSessionFactory);
            fillTable(baseParser, sqlSessionFactory);
    }

    private void createTable(BaseParser baseParser, SqlSessionFactory sqlSessionFactory) {

            int i = -1;
            for (Map.Entry<String, LinkedHashSet<String>> oldColumnList : baseParser.getContainerKeyList().entrySet()) {
                i++;
                String tableName = baseParser.getFileName() + "_" + i;

                LinkedHashSet<String> columnList = new LinkedHashSet<>();
                int z = 0;
                for (String columnName : oldColumnList.getValue()) {
                    if (columnName.equals(baseParser.getColumnSubject())) {
                        columnList.add(baseParser.getColumnSubject());
                    }else{
                        z++;
                        //skip "id"
                        if (! columnName.equals("id")) {
                            columnList.add("a" + z);
                        }
                    }
                }

                try(SqlSession session = sqlSessionFactory.openSession(true)){
                    IMapperUpdate mapper = session.getMapper(IMapperUpdate.class);
                    mapper.dropTable(tableName);
                    mapper.createTable(tableName, columnList);
                }
            }
    }

    private void fillTable(BaseParser baseParser, SqlSessionFactory sqlSessionFactory) {

            int i = -1;
            for (Map.Entry<String, ArrayList<LinkedHashMap<String, String>>> group : baseParser.getContainerKeyValueList().entrySet()) {
                i++;
                String groupName = group.getKey();
                String tableName = baseParser.getFileName() + "_" + i;

                //only use value, throw away key
                ArrayList<ArrayList<String>> newGroup = new ArrayList<>();

                for (LinkedHashMap<String, String> row : group.getValue()) {
                    ArrayList<String> valueOnly = new ArrayList<>();
                    for (Map.Entry<String, String> entry : row.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();

                        //skip "id"
                        if (! key.equals("id")) {
                            //lowercase if all uppercase, to demonstrate data transformation
                            if (baseParser.getUpperCaseList().containsKey(key)) {
                                valueOnly.add(value.toLowerCase());
                            }else{
                                valueOnly.add(value);
                            }
                        }

                    }

                    newGroup.add(valueOnly);
                }

                LinkedHashSet<String> columnList = new LinkedHashSet<>();
                int z = 0;
                for (String columnName : baseParser.getContainerKeyList().get(groupName)) {
                    if (columnName.equals(baseParser.getColumnSubject())) {
                        columnList.add(baseParser.getColumnSubject());
                    }else{
                        z++;

                        //skip "id"
                        if (! columnName.equals("id")) {
                            columnList.add("a" + z);
                        }

                    }

                }

                try(SqlSession session = sqlSessionFactory.openSession(true)){
                    IMapperInsert mapper = session.getMapper(IMapperInsert.class);
                    mapper.insert(tableName, columnList, newGroup);
                }

        }

    }

}
