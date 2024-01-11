package org.bayisehat.rdb2fhir.core.rdb2ol;

import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuadrupleTest implements HasConformance {

    private QuadrupleFactory quadrupleFactory;

    private Quadruple quadruple;

    @BeforeAll
    void setUp() throws IOException {
        quadrupleFactory = new QuadrupleFactory(getConformance());
    }

    @BeforeEach
    void setEach() {
        quadruple = quadrupleFactory.create();
    }


    @Test
    void setClass() throws RDB2OLException {
        quadruple.setClass("Patient");
        assertEquals("Patient", quadruple.getStructureDefinition().getType());
    }

    @Test
    void setClassException() {
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.setClass("UnknownResource"));
        assertEquals(1001, exception.getErrorCode());
    }


    @Test
    void setTableName() throws RDB2OLException {
        quadruple.setTableName("pasien");
        assertEquals("pasien", quadruple.getTableName());
    }

    @Test
    void setQuery() throws RDB2OLException {
        quadruple.setQuery("select * from pasien");
        assertEquals("select * from pasien", quadruple.getQuery());
    }

    @Test
    void setTableAfterQueryException() throws RDB2OLException {
        quadruple.setQuery("select * from pasien");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.setTableName("pasien"));
        assertEquals(1002, exception.getErrorCode());
    }

    @Test
    void setQueryAfterTableException() throws RDB2OLException {
        quadruple.setTableName("pasien");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.setQuery("select * from pasien"));
        assertEquals(1003, exception.getErrorCode());
    }


    @Test
    void addIdentifierExceptionBeforeSetClass() {
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.addIdentifier("Patient", new HashSet<>()));
        assertEquals(1004, exception.getErrorCode());
    }

    @Test
    void addIdentifierExceptionNullColumn() throws RDB2OLException {
        quadruple.setClass("Patient");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.addIdentifier("Patient", new HashSet<>()));
        assertEquals(1005, exception.getErrorCode());
    }

    @Test
    void addIdentifier() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("Patient", new HashSet<>(Arrays.asList("aaa")));
        assertEquals("", quadruple.getRootIdentifier().getPath().getAppPath());
        assertEquals("", quadruple.getRootIdentifier().getPath().getFhirPath());
        assertEquals("Patient", quadruple.getRootIdentifier().getPath().getAppFullPath());
        assertEquals("Patient", quadruple.getRootIdentifier().getPath().getFhirFullPath());
    }

    @Test
    void addIdentifierMultiLevel() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("Patient.id", new HashSet<>(Arrays.asList("aaa")));
        assertEquals("id", quadruple.getIdentifierByPath("id").getPath().getAppPath());
        assertEquals("id", quadruple.getIdentifierByPath("id").getPath().getFhirPath());
        assertEquals("Patient.id", quadruple.getIdentifierByPath("id").getPath().getAppFullPath());
        assertEquals("Patient.id", quadruple.getIdentifierByPath("id").getPath().getFhirFullPath());
    }


    @Test
    void addIdentifierRelativePath() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("", new HashSet<>(Arrays.asList("aaa")));
        assertEquals("", quadruple.getRootIdentifier().getPath().getAppPath());
        assertEquals("", quadruple.getRootIdentifier().getPath().getFhirPath());
        assertEquals("Patient", quadruple.getRootIdentifier().getPath().getAppFullPath());
        assertEquals("Patient", quadruple.getRootIdentifier().getPath().getFhirFullPath());
    }


    @Test
    void addIdentifierDuplicated() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("", new HashSet<>(Arrays.asList("aaa")));
        RDB2OLException exception =  assertThrows(RDB2OLException.class,
                () -> quadruple.addIdentifier("Patient", new HashSet<>(Arrays.asList("aaa"))));
        assertEquals(1006, exception.getErrorCode());
    }

    @Test
    void addMappingExceptionBeforeSetClass() {
        RDB2OLException exception =  assertThrows(RDB2OLException.class, () -> quadruple.addMapping(null, null, new HashSet<>()));
        assertEquals(1007, exception.getErrorCode());
    }

    @Test
    void addMappingExceptionEmptyPath() throws RDB2OLException {
        quadruple.setClass("Patient");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.addMapping("", "a function", new HashSet<>()));
        assertEquals(1008, exception.getErrorCode());
    }

    @Test
    void addMappingExceptionNoFunctionAndColumn() throws RDB2OLException {
        quadruple.setClass("Patient");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> quadruple.addMapping("name", null, new HashSet<>()));
        assertEquals(1009, exception.getErrorCode());
    }

    @Test
    void addMappingExceptionNoFunctionMultipleColumn() throws RDB2OLException {
        quadruple.setClass("Patient");
        RDB2OLException exception =  assertThrows(RDB2OLException.class, () -> quadruple.addMapping("name", null, new HashSet<>(Arrays.asList("aaa", "bbb"))));
        assertEquals(1010, exception.getErrorCode());
    }

    @Test
    void addMappingExceptionRootPath() throws RDB2OLException {
        quadruple.setClass("Patient");
        RDB2OLException exception =  assertThrows(RDB2OLException.class, () -> quadruple.addMapping("Patient", "adhfhasdj", null));
        assertEquals(1023, exception.getErrorCode());
    }

    @Test
    void addMappingFunctionProvided() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("name", "return False", new HashSet<>());
        assertEquals("return False", quadruple.getMappingItemList().get(0).getFunction());
    }

    @Test
    void addMappingFullyPath() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("Patient.name", "return False", new HashSet<>());
        assertEquals("name", quadruple.getMappingItemList().get(0).getPath().getAppPath());
        assertEquals("name", quadruple.getMappingItemList().get(0).getPath().getFhirPath());
        assertEquals("Patient.name", quadruple.getMappingItemList().get(0).getPath().getAppFullPath());
        assertEquals("Patient.name", quadruple.getMappingItemList().get(0).getPath().getFhirFullPath());
    }

    @Test
    void addMappingRelativePath() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("name", "return False", new HashSet<>());
        assertEquals("name", quadruple.getMappingItemList().get(0).getPath().getAppPath());
        assertEquals("name", quadruple.getMappingItemList().get(0).getPath().getFhirPath());
        assertEquals("Patient.name", quadruple.getMappingItemList().get(0).getPath().getAppFullPath());
        assertEquals("Patient.name", quadruple.getMappingItemList().get(0).getPath().getFhirFullPath());
    }

    @Test
    void addMappingImplicitType() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("multipleBirthBoolean", "return False", new HashSet<>());
        assertEquals("multipleBirth{boolean}", quadruple.getMappingItemList().get(0).getPath().getAppPath());
        assertEquals("multipleBirthBoolean", quadruple.getMappingItemList().get(0).getPath().getFhirPath());
        assertEquals("Patient.multipleBirth{boolean}", quadruple.getMappingItemList().get(0).getPath().getAppFullPath());
        assertEquals("Patient.multipleBirthBoolean", quadruple.getMappingItemList().get(0).getPath().getFhirFullPath());
    }

    @Test
    void addMappingExplicitType() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("multipleBirth{boolean}", "return False", new HashSet<>());
        assertEquals("multipleBirth{boolean}", quadruple.getMappingItemList().get(0).getPath().getAppPath());
        assertEquals("multipleBirthBoolean", quadruple.getMappingItemList().get(0).getPath().getFhirPath());
        assertEquals("Patient.multipleBirth{boolean}", quadruple.getMappingItemList().get(0).getPath().getAppFullPath());
        assertEquals("Patient.multipleBirthBoolean", quadruple.getMappingItemList().get(0).getPath().getFhirFullPath());
    }

    @Test
    void addMappingMixType() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("contained{Patient}.multipleBirthBoolean", "return False", new HashSet<>());
        assertEquals("contained{Patient}.multipleBirth{boolean}", quadruple.getMappingItemList().get(0).getPath().getAppPath());
        assertEquals("contained{Patient}.multipleBirthBoolean", quadruple.getMappingItemList().get(0).getPath().getFhirPath());
        assertEquals("Patient.contained{Patient}.multipleBirth{boolean}", quadruple.getMappingItemList().get(0).getPath().getAppFullPath());
        assertEquals("Patient.contained{Patient}.multipleBirthBoolean", quadruple.getMappingItemList().get(0).getPath().getFhirFullPath());
    }

    @Test
    void addMappingMultipleColumnProvided() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("name", "return 'eee'z", new HashSet<>(Arrays.asList("aaa", "bbb")));
        assertEquals(2 , quadruple.getMappingItemList().get(0).getColumnList().size());
    }



    @Test
    void getColumnNameList() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("", new HashSet<>(Arrays.asList("wakwak")));
        quadruple.addMapping("name", "return 'eee'z", new HashSet<>(Arrays.asList("aaa", "bbb")));
        quadruple.addMapping("telecom", "return 'eee'z", new HashSet<>(Arrays.asList("ccc")));

        assertEquals(4 , quadruple.getColumnNameList().size());
    }



    @Test
    void isTableName() throws RDB2OLException {
        quadruple.setTableName("pasien");
        assertTrue(quadruple.isTableName());
    }

    @Test
    void isTableNameFalse() throws RDB2OLException {
        quadruple.setQuery("select * from pasien");
        assertFalse(quadruple.isTableName());
    }

    @Test
    void getIdentifierListExceptionDuplicate() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("", new HashSet<>(Arrays.asList("wakwak")));
        quadruple.addIdentifier("name", new HashSet<>(Arrays.asList("wak")));
        RDB2OLException exception =  assertThrows(RDB2OLException.class, () -> quadruple.addIdentifier("name",
                new HashSet<>(Arrays.asList("dfsdf, dfsdfs"))));
        assertEquals(1006, exception.getErrorCode());
    }

    @Test
    void getIdentifierList() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addIdentifier("", new HashSet<>(Arrays.asList("wakwak")));
        quadruple.addIdentifier("name", new HashSet<>(Arrays.asList("wak")));
        assertEquals(2, quadruple.getIdentifierList().size());
    }

    @Test
    void pathDuplicateException() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("name", "return 'eee'z", new HashSet<>(Arrays.asList("aaa", "bbb")));
        quadruple.addMapping("telecom", "return 'eee'z", new HashSet<>(Arrays.asList("ccc")));

        RDB2OLException exception = assertThrows(RDB2OLException.class,  () -> quadruple.addMapping("telecom", "return 'eee'z",
                new HashSet<>()));
        assertEquals(1011, exception.getErrorCode());
    }

    @Test
    void rootIdentifierNotFoundException(){
        RDB2OLException exception = assertThrows(RDB2OLException.class,  () -> quadruple.getRootIdentifier());
        assertEquals(1012, exception.getErrorCode());
    }

    @Test
    void pathContainedNotHaveTypeExtension() throws RDB2OLException {
        quadruple.setClass("Patient");
        RDB2OLException exception = assertThrows(RDB2OLException.class,
                () -> quadruple.addMapping("contained", "return 'eee'z", null));
        assertEquals(1025, exception.getErrorCode());
    }
    @Test
    void addMappingItemListExceptionDuplicate() throws RDB2OLException {
        quadruple.setClass("Patient");
        quadruple.addMapping("contained{Patient}", "return 'eee'z", null);
        RDB2OLException exception = assertThrows(RDB2OLException.class,
                () -> quadruple.addMapping("contained{Patient}", "return 'eee'z", null));
        assertEquals(1011, exception.getErrorCode());
    }
}