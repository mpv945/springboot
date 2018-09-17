package org.haijun.study.batchJob.config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * asyncSupported 指定Servlet是否支持异步操作模式
 * displayName  指定Servlet显示名称
 * initParams  配置初始化参数
 * loadOnStartup 默认不配置或数值为负数时表示客户端第一次请求Servlet时再加载;  0或正数表示启动应用就加载，正数情况下，数值越小，加载该Servlet的优先级越高；
 * name  指定Servlet名称
 * urlPatterns/value   这两个属性作用相同，指定Servlet处理的url ;/*或者/：拦截所有
 */
@WebServlet(name="customServlet",displayName="customServlet"
        ,urlPatterns = "/myServlet/test",loadOnStartup = 1,
        initParams = {
                @WebInitParam(name="name", value="小明"),
                @WebInitParam(name="pwd", value="123456")
        })
public class CustomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter pw = resp.getWriter();
        pw.append("Hello Servlet!<br>" );
        //servletName
        pw.append("servletName：" + getServletName() + "<br>");
        // 获取初始化参数
        ServletConfig servletConfig = this.getServletConfig();
        Enumeration<String> paramNames = servletConfig.getInitParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            pw.append(paramName + "：" + servletConfig.getInitParameter(paramName) + "<br>");

        }
        pw.close();
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
