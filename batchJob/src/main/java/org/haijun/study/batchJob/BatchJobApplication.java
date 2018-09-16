package org.haijun.study.batchJob;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Log
/*@EntityScan("org.haijun.study.batchJob.model")
@EnableJpaRepositories("org.haijun.study.batchJob.repository")
@EnableTransactionManagement*/
@SpringBootApplication
@ServletComponentScan
public class BatchJobApplication {

    //protected static final Logger log = LoggerFactory.getLogger(BatchJobApplication.class);

    /**
     * 第一个spring boot项目
     * @param args
     */
    public static void main(String[] args) {
        log.info("服务开始启动");
        SpringApplication.run(BatchJobApplication.class, args);
        //new SpringApplicationBuilder(BatchJobApplication.class)

                // 替换浏览器的图标在src/main/resources下的static文件夹中放入新的ico即可自动替换，只要保持文件名为【favicon.ico】
                // 需要注意的是如果使用了WebMvcConfigurer（或者相同功能的拦截器）的话，需要加上**.ico以防止拦截器拦截ico文件的访问
                // registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns(noInterceptor).excludePathPatterns("/**.html", "/**.ico");


                //自定义启动图标在/src/main/resources/新建一个banner.txt文件。文字 http://patorjk.com/software/taag和http://www.network-science.de/ascii/
                // 图片生成https://www.degraeve.com/img2txt.php；SpringBoot可以代印动态Banner。在resources文件夹下新建banner.gif。
                //Mode.OFF为关 默认是CONSOLE的，即只打印到控制台，LOG也可以输出到日志文件。
                //.bannerMode(Banner.Mode.OFF)
                //.run(args);
        log.info("服务启动成功");
    }
}
