package org.bayisehat.rdb2fhir.core;

import ca.uhn.fhir.context.FhirContext;
import org.apache.commons.io.FileUtils;
import org.bayisehat.rdb2fhir.core.RDB2FHIR.OutputFormat;
import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.DatabaseFetcher;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "RDB2FHIR",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "A tool to generate FHIR resources from a relational database")
public class Main implements Callable<Integer> {

    public enum RDBMS{
        POSTGRESQL, MYSQL
    }

    @Option(names = { "-u", "--url" }, paramLabel = "URL", description = "Database URL", required = true)
    private String url;

    @Option(names = { "-U", "--user" }, paramLabel = "USER", description = "Database user (default: empty string)", defaultValue = "")
    private String user;

    @Option(names = { "-P", "--pass" }, paramLabel = "PASSWORD", description = "Database password (default: empty string)", defaultValue = "")
    private String password;

    @Option(names = { "d", "--db"}, paramLabel = "RDBMS", description = "Relational Database Management System: POSTGRESQL, MYSQL", required = true)
    private RDBMS rdbms;

    @Option(names = { "-f", "--format" }, paramLabel = "FORMAT", description = "FHIR Output Format: JSON (default), XML, or RDF", defaultValue = "JSON")
    private OutputFormat format;

    @Parameters(paramLabel = "<RDB2OL path>", description = "path to mapping file (RDB2OL)")
    private String rdb2olPath;

    @Parameters(paramLabel = "<output path>", description = "path to generated FHIR resources")
    private String outputPath;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        run(rdbms, url, user, password, format, rdb2olPath, outputPath);
        return 0;
    }

    public void run(RDBMS rdbms, String url, String user, String password,
                    OutputFormat format, String rdb2olPath, String outputPath) throws Exception {
        RDB2FHIR rdb2fhir = RDB2FHIRFactory.getInstance(
                new DatabaseFetcher(
                        getConnectionService(rdbms, url, user, password),
                        new ResultQuadrupleFactory()),
                FhirContext.forR4());

        rdb2fhir.setOutputFormat(format);

        generate(rdb2fhir, format, rdb2olPath, outputPath);
    }

    private void generate(RDB2FHIR rdb2FHIR, OutputFormat format, String rdb2olPath, String outputPath) throws Exception {
        String output = rdb2FHIR.transform(getRdb2ol(rdb2olPath));
        write(output, format, outputPath);
    }

    private void write(String str, OutputFormat format, String path) throws Exception {
        String fileName = "output";
        switch (format) {
            case RDF -> fileName = String.join(".", fileName, "ttl");
            case XML -> fileName = String.join(".", fileName, "xml");
            case JSON -> fileName = String.join(".", fileName, "json");
            default -> throw new Exception("Unsupported Output Format");
        }

        FileUtils.writeStringToFile(new File(path + File.separator + fileName), str, (String) null);
    }

    private ConnectionService getConnectionService(RDBMS rdbms, String url, String userName, String password) throws Exception {
        return new ConnectionService(getJdbcDriver(rdbms), getJdbcUrl(rdbms, url), userName, password);
    }

    private String getJdbcDriver(RDBMS rdbms) throws Exception {
        String jdbcDriver;
        switch (rdbms) {
            case MYSQL -> jdbcDriver = "com.mysql.cj.jdbc.Driver";
            case POSTGRESQL -> jdbcDriver = "org.postgresql.Driver";
            default -> throw new Exception("Unsupported RDBMS");
        }

        return jdbcDriver;
    }

    private String getJdbcUrl(RDBMS rdbms, String url) throws Exception {
        String jdbcUrl;
        String prefix = "jdbc:";
        switch (rdbms) {
            case MYSQL -> jdbcUrl = prefix + "mysql://" + url;
            case POSTGRESQL -> jdbcUrl = prefix +  "postgresql://" + url;
            default -> throw new Exception("Unsupported RDBMS");
        }

        return jdbcUrl;
    }

    private String getRdb2ol(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        return br.lines().collect(Collectors.joining());
    }

}