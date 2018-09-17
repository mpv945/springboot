package org.haijun.study.batchJob.quartz.trigger;

import org.haijun.study.batchJob.quartz.jobs.CalculateJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class CalculateTask {
    //时间配置文件
    //@Value("${job.calculateTriggerCron}")
    private String calculateTriggerCron="0 0 23 * * ?"; //秒，分钟，小时，天，月，年，星期       这个配置的意思是 每天的 23：0：0

    @Bean(name="calculateDetail")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        //生成JobDetail
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(CalculateJob.class);  //设置对应的Job
        factory.setGroup("calculateGroup");
        factory.setName("calculateJob");
        return factory;
    }

    @Bean(name="calculateTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        //设置JobDetail
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(1000);
        stFactory.setName("calculateTrigger");
        stFactory.setGroup("calculateGroup");
        stFactory.setCronExpression(calculateTriggerCron);  //定时任务时间配置
        return stFactory;
    }
}
