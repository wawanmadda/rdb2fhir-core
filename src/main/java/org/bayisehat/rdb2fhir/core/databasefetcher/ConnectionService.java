package org.bayisehat.rdb2fhir.core.databasefetcher;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class ConnectionService {
    private final Configuration configuration;

    private SqlSessionFactory sqlSessionFactory;

    public ConnectionService(String driver, String url, String userName, String password) {
        //obtain connection for mybatis
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource(driver, url, userName, password));
        configuration = new Configuration(environment);
        configuration.addMapper(SelectMapper.class);

       rebuildSqlSessionFactory();
    }

    public void rebuildSqlSessionFactory() {
        sqlSessionFactory =  new SqlSessionFactoryBuilder().build(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private DataSource dataSource(String driver, String url, String userName, String password) {
        return new PooledDataSource(driver, url, userName, password);
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


}
