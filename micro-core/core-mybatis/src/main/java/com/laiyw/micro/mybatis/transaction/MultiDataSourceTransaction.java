package com.laiyw.micro.mybatis.transaction;

import com.laiyw.micro.mybatis.dynamic.DynamicDataSource;
import com.laiyw.micro.mybatis.dynamic.DynamicDatasourceHolder;
import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:44
 * @Description TODO
 */
@Slf4j
@Deprecated
public class MultiDataSourceTransaction implements Transaction {

    private final DynamicDataSource dataSource;
    private Connection mainConnection;
    private DynamicDataSourceType dynamicDataSourceType;
    private final Set<Connection> readConnections;
    private boolean isConnectionTransactional;
    private boolean autoCommit;

    public MultiDataSourceTransaction(DynamicDataSource dataSource) {
        this.dataSource = dataSource;
        this.readConnections = new HashSet<>();
        dynamicDataSourceType = DynamicDatasourceHolder.getDatasourceType();
    }

    @Override
    public Connection getConnection() throws SQLException {
        DynamicDataSourceType dataSourceType = DynamicDatasourceHolder.getDatasourceType();
        if (null == dataSourceType || dataSourceType.equals(dynamicDataSourceType)) {
            if (mainConnection != null) {
                return mainConnection;
            } else {
                openMainConnection();
                dynamicDataSourceType = dataSourceType;
                return mainConnection;
            }
        } else {
            try {
                Connection conn = dataSource.getConnection();
                readConnections.add(conn);
                return conn;
            } catch (SQLException ex) {
                throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
            }
        }
    }

    private void openMainConnection() throws SQLException {
        this.mainConnection = DataSourceUtils.getConnection(this.dataSource);
        this.autoCommit = this.mainConnection.getAutoCommit();
        this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.mainConnection, this.dataSource);
        log.debug("JDBC Connection [{}] will" + (this.isConnectionTransactional ? " " : " not ") + "be managed by Spring", this.mainConnection);
    }

    @Override
    public void commit() throws SQLException {
        if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            log.debug("Committing JDBC Connection [{}]", this.mainConnection);
            this.mainConnection.commit();
            for (Connection connection : readConnections) {
                connection.commit();
            }
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            log.debug("Rolling back JDBC Connection [{}]", this.mainConnection);
            this.mainConnection.rollback();
            for (Connection connection : readConnections) {
                connection.rollback();
            }
        }
    }

    @Override
    public void close() {
        DataSourceUtils.releaseConnection(this.mainConnection, this.dataSource);
        for (Connection connection : readConnections) {
            DataSourceUtils.releaseConnection(connection, this.dataSource);
        }
    }

    @Override
    public Integer getTimeout() {
        return null;
    }
}
