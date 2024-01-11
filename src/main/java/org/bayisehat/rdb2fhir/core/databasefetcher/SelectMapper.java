package org.bayisehat.rdb2fhir.core.databasefetcher;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SelectMapper {

    List<HashMap<String, Object>> selectAllFrom(@Param("tableName") String tableName);

    List<HashMap<String, Object>> select(@Param("query") String query);


}
