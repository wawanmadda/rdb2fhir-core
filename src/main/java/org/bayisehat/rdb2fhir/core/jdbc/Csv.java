package org.bayisehat.rdb2fhir.core.jdbc;

public class Csv implements IDriver{
    @Override
    public String getJdbcDriver() {
        return "org.relique.jdbc.csv.CsvDriver";
    }

    @Override
    public String getJdbcUrl(String path) {
        String separator = "";
        //set comma as default separator if separator not provided
        if(! path.contains("separator=")){
            separator = "&separator=,";
        }

        //set file extension to csv if not provided
        String extension = "";
        if(! path.contains("fileExtension=")){
            extension = "&fileExtension=.csv";
        }

        String result =  "jdbc:relique:csv:" + path + (path.contains("?") ? "" : "?") + separator + extension;

        return result.replace("?&", "?");
    }
}
