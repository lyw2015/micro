package com.laiyw.micro.seata.aspect;

import com.laiyw.micro.frame.common.utils.StringUtils;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/27 16:47
 * @Description Global Transactional Aspect
 */
@Slf4j
@Aspect
@Component
public class GlobalTransactionalAspect {

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.laiyw.micro..service.impl..*.*(..))")
    public void doRecoveryActions(JoinPoint joinPoint, Throwable e) throws TransactionException {
        log.error("异常信息: {}", e.getMessage());
        log.error("业务方法: {}", joinPoint.getSignature().toLongString());
        log.error("业务参数： {}", Arrays.toString(joinPoint.getArgs()));
        String xid = RootContext.getXID();
        if (StringUtils.isNotBlank(xid)) {
            log.error("回滚全局事务ID: {}", xid);
            GlobalTransactionContext.reload(xid).rollback();
        }
    }
}
