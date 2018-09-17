package org.haijun.study.batchJob.config;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 过滤器（处于客户端与服务器资源文件之间的一道过滤网，例如过滤敏感词汇，压缩相应信息，url级别的权限访问控制）
 * @author haijun
 * @date   2017/11/2.
 */
@Log4j2

//@Component 主类添加@ServletComponentScan 可支持@WebServlet，@WebFilter，@WebListener 扫描
@WebFilter(filterName = "customFilter", urlPatterns = {"/*"},servletNames = "customFilter",
        initParams={@WebInitParam(name="noLoginPaths", value="index.jsp;fail.jsp")})//asyncSupported = true 异步
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Enumeration<String> enums = filterConfig.getInitParameterNames();
        String param = filterConfig.getInitParameter("noLoginPaths");
        log.debug("容器启动，过滤器初始化开始时间：{},初始化设置自定义参数：{}",LocalDateTime.now(),param);
        /** 获取初始化设置自定义参数失败原因解决，需要配置servletNames和filterName一致*/
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String param = request.getServletContext().getInitParameter("noLoginPaths");
        log.debug("CustomFilter过滤器开始过滤，过滤时间：{}，初始化设置自定义参数：{}",LocalDateTime.now(),param);
        chain.doFilter(request,response);//请求传递下去，通过。调用下一个doFilter；FilterChain的执行遵循先进后出原则

    }

    @Override
    public void destroy() {
        log.debug("容器关闭，过滤器销毁时间：{}",LocalDateTime.now());
    }
}
