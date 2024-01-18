package org.bayisehat.rdb2fhir.core;

import ca.uhn.fhir.context.FhirContext;
import org.apache.commons.io.FileUtils;
import org.bayisehat.rdb2fhir.core.RDB2FHIR.OutputFormat;
import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.DatabaseFetcher;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.jdbc.DriverFactory;
import org.bayisehat.rdb2fhir.core.jdbc.IDriver;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "java -jar rdb2fhir.jar",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "A tool to generate FHIR resources from a relational database")
public class Main implements Callable<Integer> {

    public enum DATA_SOURCE_SYSTEM {
        POSTGRESQL, MYSQL, CSV
    }

    @Option(names = { "-u", "--url" }, paramLabel = "URL/PATH", description = "Data Source URL/Path", required = true)
    private String url;

    @Option(names = { "-U", "--user" }, paramLabel = "USER", description = "Data Source user (default: empty string)", defaultValue = "")
    private String user;

    @Option(names = { "-P", "--pass" }, paramLabel = "PASSWORD", description = "Data Source password (default: empty string)", defaultValue = "")
    private String password;

    @Option(names = { "-s", "--source"}, paramLabel = "DATA_SOURCE_SYSTEM", description = "POSTGRESQL, MYSQL, or CSV", required = true)
    private DATA_SOURCE_SYSTEM dataSource;

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
         run(dataSource, url, user, password, format, rdb2olPath, outputPath);
        return 0;
    }

    public void run(DATA_SOURCE_SYSTEM dataSourceSystem, String url, String user, String password,
                    OutputFormat format, String rdb2olPath, String outputPath) throws Exception {
        RDB2FHIR rdb2fhir = RDB2FHIRFactory.getInstance(
                new DatabaseFetcher(
                        getConnectionService(dataSourceSystem, url, user, password),
                        new ResultQuadrupleFactory()),
                FhirContext.forR4());

        rdb2fhir.setOutputFormat(format);

        write(generate(rdb2fhir, format, rdb2olPath), format, outputPath);
    }

    private String generate(RDB2FHIR rdb2FHIR, OutputFormat format, String rdb2olPath) throws Exception {
        return rdb2FHIR.transform(getRdb2ol(rdb2olPath));

    }

    private void write(String str, OutputFormat format, String path) throws Exception {
        String fileName = "output";
        switch (format) {
            case RDF -> fileName = String.join(".", fileName, "ttl");
            case XML -> fileName = String.join(".", fileName, "xml");
            case JSON -> fileName = String.join(".", fileName, "json");
            default -> throw new Exception("Unsupported Output Format");
        }

        FileUtils.writeStringToFile(new File(path.equals("") ? fileName : path + File.separator + fileName), str, (String) null);
    }

    private ConnectionService getConnectionService(DATA_SOURCE_SYSTEM dataSourceSystem, String url, String userName, String password) throws Exception {
        IDriver driver = new DriverFactory().getDriver(dataSourceSystem);
        return new ConnectionService(driver.getJdbcDriver(), driver.getJdbcUrl(url), userName, password);
    }

    private String getRdb2ol(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        return br.lines().collect(Collectors.joining());
    }

}