package org.haijun.study.batchJob.config;

import lombok.extern.log4j.Log4j2;
import org.haijun.study.batchJob.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 监听器，可以用于监听web应用中某些对象或信息的创建，销毁，增加，修改，删除，等动作的发送。然后做出相应的响应处理。
 *      （在线人数，和在线用户，系统加载时进行初始化，统计网站的访问量等）
 *  监听事件可以分为3类：
 *      1. 监听对象的创建和销毁： 如 ServletContextListener
 *      2. 监听对象域中的属性增加和删除，如：HttpSessionListener，ServletRequestListener
 *      3. 监听绑定到Session上的某个对象的状态：ServletContextAttributeListener，ServletRequestAttributeListener，HttpSessionAttributeListener
 */
@Log4j2
@WebListener
public class CustomListener implements ServletContextListener {

    private static final String ALL_USER = "ALL_USER_LIST";
    /**
     * Spring Data Redis 为我们提供了两个模板类
     *      opsForValue 方法操作简单的属性数据
     *      opsForList  操作含有set的数据
     *      opsForSet/opsForZSet 操作Set的数据/有序Set
     *      opsForHash  操作含有hash的数据
     */
    @Autowired
    private RedisTemplate redisTemplate;//存放Redis时，键值是通过Serializer 序列化到数据库的，默认使用JdkSerializtionRedisSerializer

    @Autowired
    private StringRedisTemplate stringRedisTemplate;//只针对键值是字符串的数据进行操作；默认使用的时StringRedisSerializer

    /**
     * 当Servlet容器启动Web应用时候调用该方法（可以查询所以用户信息，利用缓存技术把用户信息数据缓存没提过系统性能）
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.debug("ServletContext 上下文初始化");
    }

    /**
     * 当Servlet容器终止Web应用时候调用该方法
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("ServletContext 上下文销毁");
    }

    private void testRedis(){
        // 新增一个key：name ，vle：test
        stringRedisTemplate.opsForValue().set("name","test",72,TimeUnit.HOURS);
        // 获取
        stringRedisTemplate.opsForValue().get("name");
        // 更新
        stringRedisTemplate.opsForValue().set("name","updateTest");
        // 删除
        stringRedisTemplate.delete("name");

        // 查询全部用户(若key（ALL_USER） 不存在，则会新增一个键与其关联list，将集合数据从左到右一次插入）
        redisTemplate.opsForList().leftPushAll(ALL_USER,Arrays.asList(new User()));

        // 查询链表中的全部数据(查询从0（开始下标），-1 最后的下标)全部数据
        redisTemplate.opsForList().range(ALL_USER,0,-1);
        // 查询该key链表的对象数量
        Long size = redisTemplate.opsForList().size(ALL_USER);
    }
}
