package org.bayisehat.rdb2fhir.core.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bayisehat.rdb2fhir.core.rdb2ol.Rdb2ol;
import org.bayisehat.rdb2fhir.core.rdb2ol.Rdb2olFactory;
import org.bayisehat.rdb2fhir.core.util.FhirTool;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.OpenType;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.*;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class ConverterManager {

    private BaseParser baseParser;

    private DbSeeder dbSeeder;

    private MappingFileGenerator mappingFileGenerator;

    private ObjectMapper objectMapper;

    private Conformance conformanceService;

    private Rdb2olFactory rdb2olFactory;

    private String outputFolder;

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public ConverterManager(BaseParser baseParser, DbSeeder dbSeeder,
                            MappingFileGenerator mappingFileGenerator, ObjectMapper objectMapper,
                            Conformance conformanceService, Rdb2olFactory rdb2olFactory) {
        this.baseParser = baseParser;
         this.dbSeeder = dbSeeder;
         this.mappingFileGenerator = mappingFileGenerator;
         this.objectMapper = objectMapper;
         this.conformanceService = conformanceService;
         this.rdb2olFactory = rdb2olFactory;
    }

    public String exec(String file) throws Exception {
        baseParser.parse(file);
        dbSeeder.seed(baseParser);
        String retVal = mappingFileGenerator.generateMappingFile(baseParser);

        Rdb2ol rdb2ol = rdb2olFactory.create(objectMapper.readTree(retVal));
        String rdb2olStr = rdb2ol.asText(Rdb2ol.PathType.FHIR, Rdb2ol.PathNaming.FULL);
        //saveToFile(file, rdb2olStr);
        return rdb2olStr;
    }

    private void saveToFile(String filePath, String rdb2ol) throws IOException {
        String fileName = FilenameUtils.getName(filePath);
        FileUtils.writeStringToFile(new File(outputFolder + File.separator + fileName), rdb2ol);
    }


}
