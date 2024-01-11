package org.bayisehat.rdb2fhir.core.valueservice.jython;

import org.python.core.*;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class PyInterpreter {
    private final PythonInterpreter pi;

    public PyInterpreter(PythonInterpreter pythonInterpreter) {
        pi = pythonInterpreter;
        pi.exec("def getValue(column): return globals()[column]");
    }

    public String execute(String body) throws Exception {
        //if function body is empty
        if (body.isEmpty()) {
            throw  new Exception("Function in mapping cannot be empty");
        }

        StringBuilder functionAndResultStatement = new StringBuilder();
        appendHeaderEvaluateStatement(functionAndResultStatement);

        BufferedReader bufReader = new BufferedReader(new StringReader(body));
        String line;
        while( (line=bufReader.readLine()) != null )
        {
            functionAndResultStatement.append("\t").append(line).append(System.getProperty("line.separator"));
        }

        appendResultStatement(functionAndResultStatement);

        pi.exec(functionAndResultStatement.toString());

        return getResult();

    }

    public void initVariableColumnValue(HashMap<String, String> columnValue) {
        for (Map.Entry<String, String> entry2 : columnValue.entrySet()) {
            pi.set(entry2.getKey(), entry2.getValue());
        }
    }

    public void cleanVariableColumnValue(HashMap<String, String> columnValue) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : columnValue.entrySet()) {
            sb.append("del globals()[\"").append(entry.getKey()).append("\"]").append(System.getProperty("line.separator"));
        }
        sb.append("del evaluate").append(System.getProperty("line.separator"));
        sb.append("del result").append(System.getProperty("line.separator"));
        pi.exec(sb.toString());
    }

    private String getResult() {
        PyObject pyObject = pi.get("result");
        if (pyObject instanceof PyBoolean) {
            return ((PyBoolean) pyObject).getBooleanValue() ? "true" : "false";
        }
        if (pyObject instanceof PyNone) {
            return null;
        }

        //integer, float, tuple, list, dictionary
        return pyObject.toString();
    }

    private void appendHeaderEvaluateStatement(StringBuilder sb) {
        sb.append("def evaluate():").append(System.getProperty("line.separator"));
    }

    private void appendResultStatement(StringBuilder sb) {
        sb.append("result = evaluate()");
    }


}
