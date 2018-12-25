package org.haijun.study.batchJob.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ProcessBuilderExample2 {

    public static void main(String[] args) {

        //得到当前的系统属性
        System.getProperties().list(System.out);

        ExecutorService pool = Executors.newSingleThreadExecutor();

        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "ping -n 3 www.baidu.com");

        try {

            Process process = processBuilder.start();

            System.out.println("process ping...");
            ProcessReadTask task = new ProcessReadTask(process.getInputStream());
            Future<List<String>> future = pool.submit(task);

            // no block, can do other tasks here
            System.out.println("process task1...");
            System.out.println("process task2...");

            List<String> result = future.get(5, TimeUnit.SECONDS);
            for (String s : result) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private static class ProcessReadTask implements Callable<List<String>> {

        private InputStream inputStream;

        public ProcessReadTask(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public List<String> call() throws UnsupportedEncodingException {
            return new BufferedReader(new InputStreamReader(inputStream,"GBK"))//System.getProperty("file.encoding")
                    .lines()
                    .collect(Collectors.toList());
        }
    }
}
