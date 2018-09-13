package org.haijun.study.batchJob;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BatchJobApplication {

    /**
     * 第一个spring boot项目
     * @param args
     */
    public static void main(String[] args) {
        //SpringApplication.run(BatchJobApplication.class, args);
        new SpringApplicationBuilder(BatchJobApplication.class)

                // 替换浏览器的图标在src/main/resources下的static文件夹中放入新的ico即可自动替换，只要保持文件名为【favicon.ico】
                // 需要注意的是如果使用了WebMvcConfigurer（或者相同功能的拦截器）的话，需要加上**.ico以防止拦截器拦截ico文件的访问
                // registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns(noInterceptor).excludePathPatterns("/**.html", "/**.ico");


                //自定义启动图标在/src/main/resources/新建一个banner.txt文件。文字 http://patorjk.com/software/taag和http://www.network-science.de/ascii/
                // 图片生成https://www.degraeve.com/img2txt.php；SpringBoot可以代印动态Banner。在resources文件夹下新建banner.gif。
                //Mode.OFF为关 默认是CONSOLE的，即只打印到控制台，LOG也可以输出到日志文件。
                //.bannerMode(Banner.Mode.OFF)
                .run(args);

    }
}
