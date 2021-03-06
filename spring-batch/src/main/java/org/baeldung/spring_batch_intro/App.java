package org.baeldung.spring_batch_intro;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(final String[] args) {
        // Spring Java config
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringConfig.class);
        context.register(SpringBatchConfig.class);
        context.refresh();

        // Spring xml config
        // ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch.xml");

        final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        final Job job = (Job) context.getBean("firstBatchJob");
        System.out.println("Starting the batch job");
        try {
            final JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Job Status : " + execution.getStatus());
            System.out.println("Job succeeded");
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("Job failed");
        }
    }
}