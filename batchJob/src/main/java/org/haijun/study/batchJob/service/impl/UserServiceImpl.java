package org.haijun.study.batchJob.service.impl;

import com.querydsl.core.types.Predicate;
import org.haijun.study.batchJob.model.User;
import org.haijun.study.batchJob.model.querydsl.QUser;
import org.haijun.study.batchJob.repository.UserRepository;
import org.haijun.study.batchJob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service// 如果有些组件不好归类，请使用@Component
public class UserServiceImpl implements UserService {

    // 第一种依赖注解（如果时dao层，一般使用@Repository 表示持久化层）
    @Autowired// 按类型匹配，默认依赖对象必须存在，可以通过required=false指定不存在，如果想根据名称Qualifier
    //@Qualifier("userRepository")
    // 第二种依赖注解
    //@Resource// J2EE的依赖注解，默认按字段名称查找，找不到名称再按类型找，可以指定name查找（如果指定name，就只会按名称查找）
    private UserRepository userRepository;

    /**
     * 查询id集合中的用户
     *
     * @param ids
     * @return
     */
    @Override
    public List<User> findByIds(Collection<String> ids) {
        return userRepository.findByIdIn(ids);
    }

    /**
     * 分页查询
     * @param page 页数
     * @param size 每页条数
     * @return
     */
    @Override
    public Page<User> findAll(int page, int size) {
        //PageRequest pageRequest = PageRequest.of(page,size,Sort.Direction.DESC,"id");
        Sort sort=new Sort(Sort.Direction.DESC,"name").
                and(new Sort(Sort.Direction.ASC,"id"));
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        return userRepository.findAll(pageRequest);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUser(String id) {
        userRepository.deleteById(id);
        return true;
    }


    @Override
    public User findOne(String name) {
        /**
         * 该例是使用spring data QueryDSL实现
         */
        QUser quser = QUser.user;
        Predicate predicate = quser.name.eq(name);// 根据用户名，查询user表
        return userRepository.findOne(predicate).orElse(new User());// 不存在初始一个
    }
}
