package org.haijun.study.batchJob;

import com.alibaba.fastjson.JSON;
import org.haijun.study.batchJob.model.User;
import org.haijun.study.batchJob.model.UserJdbc;
import org.haijun.study.batchJob.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchJobApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    //@Autowired
    @Resource
    private UserService userService;

    /**
     * 第一个spring boot测试类
     */
    @Test
    public void testJdbcTemple() {
        RowMapper rowMapper = new RowMapper<UserJdbc>() {
            @Override
            public UserJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserJdbc userJdbc = new UserJdbc();
                userJdbc.setId(rs.getLong("id"));
                userJdbc.setCode(rs.getString("code"));
                userJdbc.setName(rs.getString("name"));
                userJdbc.setPwd(rs.getString("pwd"));
                return userJdbc;
            }
        };
        String sql = "SELECT `id`, `code`, `name`, `pwd` FROM `t_user`";
        List<UserJdbc> userJdbcList = jdbcTemplate.query(sql,rowMapper);
        System.out.println("查询成功：");
        if(!CollectionUtils.isEmpty(userJdbcList)){
            System.out.println(JSON.toJSONString(userJdbcList));
        }
    }

    @Test
    public void testJpa(){
        for(int i = 0 ;i < 10; i++){
            // 新增用户
            User user = new User();
            user.setId("1"+i);
            user.setName("海军"+i);
            user.setMail("sdfds@qq.com"+i);
            user.setPassword(Math.random()+"");
            user = userService.addUser(user);
            //Assert.isTrue(user.getName().equals("海军"+i),"添加用户成功");
        }

        Page<User> listAll = userService.findAll(1,5);
        System.out.println("总页数="+listAll.getTotalPages()+" 总条数="+listAll.getTotalElements());
        System.out.println(JSON.toJSONString(listAll));

        List<String> ids = Arrays.asList("1"+0,"1"+1);
        List<User> userByIds = userService.findByIds(ids);
        //Assert.isNull(userByIds,"根据id没查询到数据");
        for(User user: userByIds){
            user.setMail("875524554545");
            userService.addUser(user);
        }

        /*for(int i = 0 ;i < 10; i++){
            userService.deleteUser("1"+i);
        }*/
    }

}
