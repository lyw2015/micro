package com.laiyw.micro.mybatis.interceptor;

import com.laiyw.micro.mybatis.dynamic.DynamicDatasourceHolder;
import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 10:09
 * @Description TODO
 */
@Slf4j
@Component
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
        )
})
public class DynamicDataSourceInterceptor implements Interceptor {

    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        log.debug("当前方法是否已开启事务: {}", synchronizationActive);
        DynamicDataSourceType dataSourceType = DynamicDataSourceType.master;
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) objects[0];
            log.debug("执行方法: {}", mappedStatement.getId());
            if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                // 如果selectKey为自增id查询主键则使用主库
                if (!mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    if (!sql.matches(REGEX)) {
                        // 这里如果有多个从数据库，则添加挑选过程
                        dataSourceType = DynamicDataSourceType.slave;
                    }
                }
            }
        }
        DynamicDatasourceHolder.setDatasourceType(dataSourceType);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 增删改查的拦截，然后交由intercept处理
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
}
