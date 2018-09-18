package org.haijun.study.batchJob.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

// 现在我们需要创建标准QuartzJobBean实例，我们将使用它来执行批处理作业。
public class CustomQuartzJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try
        {
            Job job = jobLocator.getJob(jobName);
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            jobLauncher.run(job, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String jobName;
    private JobLauncher jobLauncher;
    private JobLocator jobLocator;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public JobLocator getJobLocator() {
        return jobLocator;
    }

    public void setJobLocator(JobLocator jobLocator) {
        this.jobLocator = jobLocator;
    }
}
