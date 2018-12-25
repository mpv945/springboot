package org.haijun.study.batchJob.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/*使用builder.directory（）更改我们的shell命令正在运行的工作目录
        使用builder.environment（）将自定义键值映射设置为环境
        将输入和输出流重定向到自定义替换
        使用builder.inheritIO（）将它们继承到当前JVM进程的流*/
public class ShellUtil {

    public static void main(String[] args) {
        try {
            runCmd();
            //runCmd2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void runCmd2() throws InterruptedException, IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            builder.command("cmd.exe", "/c", "dir");
        } else {
            builder.command("sh", "-c", "ls");
        }
        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();
        /*StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);*/
        Supplier<String> consumer = null ;
        StreamGobbler1 streamGobbler =
                new StreamGobbler1(process.getInputStream(), consumer);

        Executors.newSingleThreadExecutor().submit(streamGobbler);

        int exitCode = 0;
        while (true){
            System.out.println(consumer.get());
        }
        //assert exitCode == 0;
    }
    public static void runCmd() throws InterruptedException, IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String homeDirectory = System.getProperty("user.home");
        Process process;
        if (isWindows) {
            process = Runtime.getRuntime()
                    .exec(String.format("cmd.exe /c dir %s", homeDirectory));
        } else {
            process = Runtime.getRuntime()
                    .exec(String.format("sh -c ls %s", homeDirectory));
        }
        StringBuilder sb = new StringBuilder();
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        sb.append(s).append(System.lineSeparator());
                    }
                });
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        boolean se = process.waitFor(1, TimeUnit.SECONDS);
        process.exitValue();
        while (!se){
            System.out.println(se);
        }
        Writer w = new OutputStreamWriter(process.getOutputStream(), "UTF-8");
        w.write("send to child\n");
        System.out.println("exitCode="+exitCode);
        System.out.println("结果="+sb.toString());
        assert exitCode == 0;
        process.destroy();
        if (process.isAlive()) {
            process.destroyForcibly();
        }
    }
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }
        @Override
        public void run() {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream))) {
                bufferedReader.lines().forEach(consumer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    private static class StreamGobbler1 implements Runnable {
        private InputStream inputStream;
        private Supplier<String> consumer;

        public StreamGobbler1(InputStream inputStream, Supplier<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }
        @Override
        public void run() {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream))) {
                String result = bufferedReader.lines().parallel().collect(Collectors.joining(System.lineSeparator()));
                consumer = ()->result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
