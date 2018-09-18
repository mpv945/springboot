package org.haijun.study.batchJob.config;

import org.haijun.study.batchJob.tools.AutowiringSpringBeanJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig1 {

    // Quartz中的job自动注入spring容器托管的对象
    @Bean
    public AutowiringSpringBeanJobFactory autoWiringSpringBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

    @Autowired
    @Qualifier("calculateTrigger")
    public CronTriggerFactoryBean calculateTrigger;


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(autoWiringSpringBeanJobFactory());  //配置Spring注入的Job类  固定不变
        scheduler.setTriggers(//  SchedulerFactoryBean 中 必须设置 List<Trigger> triggers; 参数（triggers是个list容器） 对应方法 setTriggers(Trigger... triggers)；
                calculateTrigger.getObject()// ,号隔开多个　

        ); //这里可以设置多个CronTriggerFactoryBean
        return scheduler;
    }

}
