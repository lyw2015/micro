package com.laiyw.micro.mybatis.transaction;

import com.laiyw.micro.mybatis.dynamic.DynamicDataSource;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/8 20:43
 * @Description TODO
 */

@Component
public class MultiDataSourceTransactionFactory extends SpringManagedTransactionFactory {

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        if (dataSource instanceof DynamicDataSource) {
            return new MultiDataSourceTransaction((DynamicDataSource) dataSource);
        } else {
            return super.newTransaction(dataSource, level, autoCommit);
        }
    }
}
