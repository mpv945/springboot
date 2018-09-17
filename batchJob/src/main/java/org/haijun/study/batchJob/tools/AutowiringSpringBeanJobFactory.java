package org.haijun.study.batchJob.tools;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private transient AutowireCapableBeanFactory beanFactory;
    //Spring提供了一种机制让你可以获取ApplicationContext。就是ApplicationContextAware接口。
    //对于一个实现了ApplicationContextAware接口的类，Spring会实例化它的同时
    //调用它的public voidsetApplicationContext(ApplicationContext applicationContext) throws BeansException;接口，将该bean所属上下文传递给它。
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance); //这一步解决不能spring注入bean的问题
        return jobInstance;
    }
}
