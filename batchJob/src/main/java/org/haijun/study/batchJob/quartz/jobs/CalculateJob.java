package org.haijun.study.batchJob.quartz.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class CalculateJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 具体执行操作
    }
}
