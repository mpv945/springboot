package org.haijun.study.batchJob.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.haijun.study.batchJob.model.User;
import org.haijun.study.batchJob.model.querydsl.QUser;
import org.haijun.study.batchJob.repository.UserRepositoryCustom;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {

    //实体管理对象
    @PersistenceContext
    private EntityManager em;

    /*//queryDSL,JPA查询工厂
    private JPAQueryFactory queryFactory;

    //实例化查询工厂
    @PostConstruct
    public void init()
    {
        queryFactory = new JPAQueryFactory(em);
    }*/

    /**
     * 具体使用参考
     * https://blog.csdn.net/liuchuanhong1/article/details/70244261?utm_source=gold_browser_extension
     * https://www.jianshu.com/p/edfb4c2ff445
     * @return
     */
    @Override
    public User lastCreateUser() {
        // 简单查询使用JPAQuery，不需要queryFactory
        final JPAQuery<User> query = new JPAQuery<>(em);
        QUser qUser = QUser.user;
        //return query.from(qUser).where(qUser.name.eq(firstname)).list(qUser);
        return query.from(qUser).orderBy(qUser.createDate.desc()).fetchFirst();
    }
}
