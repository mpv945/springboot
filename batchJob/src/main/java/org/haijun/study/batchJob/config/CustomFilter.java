package org.haijun.study.batchJob.config;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 过滤器
 * @author haijun
 * @date   2017/11/2.
 */
@Log4j2

//@Component 主类添加@ServletComponentScan 可支持@WebServlet，@WebFilter，@WebListener 扫描
@WebFilter(filterName = "customFilter", urlPatterns = {"/*"},
        initParams={@WebInitParam(name="noLoginPaths", value="index.jsp;fail.jsp")})//asyncSupported = true 异步
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Enumeration<String> enums = filterConfig.getInitParameterNames();
        String param = filterConfig.getInitParameter("noLoginPaths");
        log.debug("容器启动，过滤器初始化开始时间：{},初始化设置自定义参数：{}",LocalDateTime.now(),param);
        // todo 获取初始化设置自定义参数失败
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String param = request.getServletContext().getInitParameter("noLoginPaths");
        log.debug("CustomFilter过滤器开始过滤，过滤时间：{}，初始化设置自定义参数：{}",LocalDateTime.now(),param);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.debug("容器关闭，过滤器销毁时间：{}",LocalDateTime.now());
    }
}
