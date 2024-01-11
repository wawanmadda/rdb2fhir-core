package org.bayisehat.rdb2fhir.core.databasefetcher;

import org.apache.ibatis.session.SqlSession;
import org.bayisehat.rdb2fhir.core.fetcher.Fetchable;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadruple;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;
import org.bayisehat.rdb2fhir.core.rdb2ol.Rdb2ol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseFetcher implements Fetchable {

    private final ConnectionService connectionService;

    private final ResultQuadrupleFactory resultQuadrupleFactory;

    public DatabaseFetcher(ConnectionService connectionService, ResultQuadrupleFactory resultQuadrupleFactory) {
        this.connectionService = connectionService;
        this.resultQuadrupleFactory = resultQuadrupleFactory;
    }

    @Override
    public ArrayList<ResultQuadruple> fetch(Rdb2ol rdb2ol)  {
        ArrayList<ResultQuadruple> retVal = new ArrayList<>();
        for (Quadruple quadruple : rdb2ol.getQuadrupleList()) {
            if (quadruple.isTableName()) {
                retVal.add(fetchTableName(quadruple));
                continue;
            }
            retVal.add(fetchQuery(quadruple));
        }
        return  retVal;
    }

    private ResultQuadruple fetchTableName(Quadruple quadruple) {
        try (SqlSession session = connectionService.getSqlSessionFactory().openSession()) {
            SelectMapper mapper = session.getMapper(SelectMapper.class);
            List<HashMap<String, Object>> resultList = mapper.selectAllFrom(quadruple.getTableName());
            return resultQuadrupleFactory.create(quadruple, resultList);
        }
    }

    private ResultQuadruple fetchQuery(Quadruple quadruple) {
        try (SqlSession session = connectionService.getSqlSessionFactory().openSession()) {
            SelectMapper mapper = session.getMapper(SelectMapper.class);
            List<HashMap<String, Object>> resultList = mapper.select(quadruple.getQuery());
            return resultQuadrupleFactory.create(quadruple, resultList);
        }
    }


}
