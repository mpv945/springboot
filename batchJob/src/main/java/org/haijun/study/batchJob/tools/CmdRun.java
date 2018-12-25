package org.haijun.study.batchJob.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CmdRun {


    //Windows下 ffmpeg.exe的路径
    // private static String ffmpegEXE = "D:\\Downloads\\ffmpeg-20180528-ebf85d3-win64-static\\bin\\ffmpeg.exe";

    //Linux与mac下  ffmpeg的路径
    private static String ffmpegEXE = "D:\\soft\\ffmpeg-4.1-win64-static\\bin\\ffprobe.exe";

    public static void main(String[] args) {
        try {
            getInfo("F:\\视频\\[杜拉拉升职记2]Go.Lala.Go.2.201" +
                    "5.BluRay.720p.x264.AC3-CnSCG.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getInfo1(String videoPath) throws Exception {
        StringBuilder cmd = new StringBuilder(ffmpegEXE);
        cmd.append(" -v 1 -of json -show_format").append(" ").append(videoPath);
        System.out.println(cmd);
        Process proc = Runtime.getRuntime().exec(cmd.toString());
        InputStream errorStream = proc.getInputStream();
        String result = new BufferedReader(new InputStreamReader(errorStream))
                .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(result);
    }

    public static void getInfo(String videoPath) throws Exception {

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-v");
        command.add("debug");
        command.add("-of");
        command.add("json");
        command.add("-show_format");
        command.add("-show_streams");
        //command.add("-v 1 -of json -show_format");
        command.add(videoPath);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 使用这种方式会在瞬间大量消耗CPU和内存等系统资源，所以这里我们需要对流进行处理
        InputStream errorStream = process.getInputStream();
       /* InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);*/

        String result = new BufferedReader(new InputStreamReader(errorStream))
                .lines().parallel().collect(Collectors.joining(System.lineSeparator()));

/*        String line = "";
        while ((line = br.readLine()) != null) {
        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }*/
        if (errorStream != null) {
            errorStream.close();
        }

        System.out.println(result);
    }
}
