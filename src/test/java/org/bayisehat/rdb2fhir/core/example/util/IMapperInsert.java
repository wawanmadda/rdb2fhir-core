package org.bayisehat.rdb2fhir.core.example.util;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public interface IMapperInsert {

      void insert(@Param("tableName") String tableName, @Param("columnList") LinkedHashSet<String> columnList, @Param("groupList") ArrayList<ArrayList<String>> groupList);

}
