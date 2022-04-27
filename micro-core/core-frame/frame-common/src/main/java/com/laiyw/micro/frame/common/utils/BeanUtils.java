package com.laiyw.micro.frame.common.utils;

import com.laiyw.micro.frame.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/27 9:06
 * @Description TODO
 */

@Slf4j
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 使用CGLib的BeanCopier进行对象拷贝
     *
     * @param source      源对象
     * @param targetClass 目标对象
     * @param <T1>
     * @param <T2>
     * @return
     */
    public static <T1, T2> T2 createCopy(T1 source, Class<T2> targetClass) {
        if (source == null) {
            throw new ServiceException("参数异常");
        } else {
            T2 target;
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
            beanCopier.copy(source, target, null);
            return target;
        }
    }
}
