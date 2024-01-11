package org.bayisehat.rdb2fhir.core.example.util;

import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashSet;

public interface IMapperUpdate {
      void createTable(@Param("tableName") String tableName, @Param("columnList") LinkedHashSet<String> columnList);

      void dropTable(@Param("tableName") String tableName);

      void dropSchema(@Param("schemaName") String schemaName);
      void createSchema(@Param("schemaName") String schemaName);
      void grantAll(@Param("schemaName") String schemaname, @Param("user") String user);

}
