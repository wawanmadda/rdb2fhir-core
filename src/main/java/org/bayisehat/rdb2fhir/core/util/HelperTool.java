package org.bayisehat.rdb2fhir.core.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A list of methods that widely used that do not belong to a specified package
 * */
public class HelperTool {

    /**
     * Load a file from a path
     *
     * @return String
     */
    public static String loadFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        return br.lines().collect(Collectors.joining());
    }

    /**
     * Load a file as a list of string
     */
    public static List<String> loadFileAsList(String filePath) throws IOException {
        return Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());
    }





}
