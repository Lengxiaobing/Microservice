package com.cloud.dynamic.aop;

import com.cloud.dynamic.annotation.DataSource;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @Description: aop织入
 * @Author: ZX
 * @Date: 2019/3/16 16:48
 */
public class DynamicDataSourceAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private Advice advice;

    private Pointcut pointcut;

    public DynamicDataSourceAnnotationAdvisor(DynamicDataSourceAnnotationInterceptor interceptor) {
        this.advice = interceptor;
        this.pointcut = buildPointcut();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

    private Pointcut buildPointcut() {
        Pointcut cpc = new AnnotationMatchingPointcut(DataSource.class, true);
        // 类注解
        Pointcut clpc = AnnotationMatchingPointcut.forClassAnnotation(DataSource.class);
        // 方法注解
        Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(DataSource.class);
        return new ComposablePointcut(cpc).union(clpc).union(mpc);
    }

}