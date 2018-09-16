package org.haijun.study.batchJob.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import javax.sql.DataSource;

/**
 * 开启Druid的监控功能；可以通过localhost:8082/druid/index.html 查看监控
 */
//@Configuration
public class DruidConfiguration {

        //@Bean
        //@ConfigurationProperties(prefix = "spring.datasource")
        /*public DataSource druidDataSource() {
            DruidDataSource druidDataSource = new DruidDataSource();
            return druidDataSource;
        }*/

        //@Bean
/*        public ServletRegistrationBean druidStatViewServle(){
            //ServletRegistrationBean提供类的进行注册.
            ServletRegistrationBean servletRegistrationBean
                    = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
            //添加初始化参数：initParams
            //白名单： // IP白名单 (没有配置或者为空，则允许所有访问)
            //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
            //IP黑名单 (存在共同时，deny优先于allow)
            // 如果满足deny的话提示:Sorry, you are not permitted to view this page.
            servletRegistrationBean.addInitParameter("deny","192.168.1.73");
            //登录查看信息的账号和密码.
            servletRegistrationBean.addInitParameter("loginUsername","admin");
            servletRegistrationBean.addInitParameter("loginPassword","123456");
            //是否能够重置数据.
            servletRegistrationBean.addInitParameter("resetEnable","false");
            return servletRegistrationBean;
        }*/

        //@Bean
        /*public FilterRegistrationBean druidStatFilter(){
            FilterRegistrationBean filterRegistrationBean
                    = new FilterRegistrationBean(new WebStatFilter());
            //添加过滤规则.
            filterRegistrationBean.addUrlPatterns("/*");
            //添加不需要忽略的格式信息.
            filterRegistrationBean.addInitParameter("exclusions",
                    "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/**.ico");
            return filterRegistrationBean;
        }*/

        // Druid 密码加密处理
        public static void main(String[] args) {
            String password = "123456";// 你的密码
            try {
                String[] arr = ConfigTools.genKeyPair(512);
                System.out.println("privateKey:" + arr[0]);//私钥
                System.out.println("publicKey:" + arr[1]);//公钥
                System.out.println("password:" + ConfigTools.encrypt(arr[0], password));//密码
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
