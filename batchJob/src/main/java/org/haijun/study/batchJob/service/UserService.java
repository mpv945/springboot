package org.haijun.study.batchJob.service;

import org.haijun.study.batchJob.model.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface UserService {

    /**
     * 查询id集合中的用户
     * @param ids
     * @return
     */
    List<User> findByIds(Collection<String> ids);

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<User> findAll(int page, int size);

    /**
     * 新增用户
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    boolean deleteUser(String id);

    /**
     * 使用dsl 查询一个对象
     * @param name
     * @return
     */
    User findOne(String name);
}
