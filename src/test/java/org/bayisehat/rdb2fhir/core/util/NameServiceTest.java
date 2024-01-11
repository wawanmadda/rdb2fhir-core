package org.bayisehat.rdb2fhir.core.util;

import org.bayisehat.rdb2fhir.core.fhir.NameService;
import org.bayisehat.rdb2fhir.core.rdb2ol.RDB2OLException;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameServiceTest {
    private Base base;


    @BeforeEach
    void setUp() {
        base = new Patient();
    }

    @Test
    void containIndexOnly() throws RDB2OLException {
        NameService nameService = new NameService(base, "name[3]");
        assertEquals(3, nameService.getIndex());
        assertEquals("name", nameService.getNameRemovedIndex());
        assertNull(nameService.getSlice());
        assertEquals("name", nameService.getNameRemovedIndexSlice());
        assertEquals("name", nameService.getProperty().getName());
        assertEquals("name", nameService.getPropertyName());
        assertNull(nameService.getDataType());
    }

    @Test
    void containSliceOnly() throws RDB2OLException {
        NameService nameService = new NameService(base, "name:slice1");
        assertNull(nameService.getIndex());
        assertEquals("name:slice1", nameService.getNameRemovedIndex());
        assertEquals("slice1", nameService.getSlice());
        assertEquals("name", nameService.getNameRemovedIndexSlice());
        assertEquals("name", nameService.getProperty().getName());
        assertEquals("name", nameService.getPropertyName());
        assertNull(nameService.getDataType());
    }

    @Test
    void containSliceIndex() throws RDB2OLException {
        NameService nameService = new NameService(base, "name:slice1[3]");
        assertEquals(3, nameService.getIndex());
        assertEquals("name:slice1", nameService.getNameRemovedIndex());
        assertEquals("slice1", nameService.getSlice());
        assertEquals("name", nameService.getNameRemovedIndexSlice());
        assertEquals("name", nameService.getProperty().getName());
        assertEquals("name", nameService.getPropertyName());
        assertNull(nameService.getDataType());
    }

    @Test
    void containImplicitDataType() throws RDB2OLException {
        NameService nameService = new NameService(base, "multipleBirthBoolean");
        assertNull(nameService.getIndex());
        assertEquals("multipleBirthBoolean", nameService.getNameRemovedIndex());
        assertNull(nameService.getSlice());
        assertEquals("multipleBirthBoolean", nameService.getNameRemovedIndexSlice());
        assertEquals("multipleBirth[x]", nameService.getProperty().getName());
        assertEquals("multipleBirth", nameService.getPropertyName());
        assertEquals("boolean", nameService.getDataType());
    }

    @Test
    void containExplicitDataType() throws RDB2OLException {
        NameService nameService = new NameService(base, "multipleBirth{boolean}");
        assertNull(nameService.getIndex());
        assertEquals("multipleBirth{boolean}", nameService.getNameRemovedIndex());
        assertNull(nameService.getSlice());
        assertEquals("multipleBirth{boolean}", nameService.getNameRemovedIndexSlice());
        assertEquals("multipleBirth[x]", nameService.getProperty().getName());
        assertEquals("multipleBirth", nameService.getPropertyName());
        assertEquals("boolean", nameService.getDataType());
    }


    @Test
    void containedResource() throws RDB2OLException {
        NameService nameService = new NameService(base, "contained{Patient}");
        assertNull(nameService.getIndex());
        assertEquals("contained{Patient}", nameService.getNameRemovedIndex());
        assertNull(nameService.getSlice());
        assertEquals("contained{Patient}", nameService.getNameRemovedIndexSlice());
        assertEquals("contained", nameService.getProperty().getName());
        assertEquals("contained", nameService.getPropertyName());
        assertEquals("Patient", nameService.getDataType());
    }

    @Test
    void containedResourceWithIndex() throws RDB2OLException {
        NameService nameService = new NameService(base, "contained{Patient}[3]");
        assertEquals(3, nameService.getIndex());
        assertEquals("contained{Patient}", nameService.getNameRemovedIndex());
        assertNull(nameService.getSlice());
        assertEquals("contained{Patient}", nameService.getNameRemovedIndexSlice());
        assertEquals("contained", nameService.getProperty().getName());
        assertEquals("contained", nameService.getPropertyName());
        assertEquals("Patient", nameService.getDataType());
    }

    @Test
    //@todo remove this
    void underScore() throws RDB2OLException {
        NameService nameService = new NameService(base, "_name[3]");
        assertEquals(3, nameService.getIndex());
        assertEquals("_name", nameService.getNameRemovedIndex());
        assertNull(nameService.getSlice());
        assertEquals("_name", nameService.getNameRemovedIndexSlice());
        assertEquals("name", nameService.getProperty().getName());
        assertEquals("name", nameService.getPropertyName());
        assertNull(nameService.getDataType());
    }



}