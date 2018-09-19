package org.haijun.study.batchJob.config;


import org.haijun.study.batchJob.tools.AutowiringSpringBeanJobFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Configuration

public class QuartzConfig {

    @Autowired
    private List<CronTriggerFactoryBean> cronTriggerFactoryBeans;

    @Autowired
    private List<Trigger> triggers;

    @Autowired
    private List<JobDetail>  jobDetails;

    // Quartz中的job自动注入spring容器托管的对象
    @Bean
    public AutowiringSpringBeanJobFactory autoWiringSpringBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws SchedulerException {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(autoWiringSpringBeanJobFactory());
        if(!CollectionUtils.isEmpty(cronTriggerFactoryBeans)){
            Trigger[] objs = cronTriggerFactoryBeans.stream().map(obj->obj.getObject()).collect(Collectors.toList()).toArray(new Trigger[]{});
            scheduler.setTriggers(objs);
        }
        if(!CollectionUtils.isEmpty(triggers)){
            scheduler.setTriggers(triggers.toArray(new Trigger[]{}));
            scheduler.setJobDetails(jobDetails.toArray(new JobDetail[]{}));
        }
        return scheduler;
    }

}
