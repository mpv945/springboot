package org.haijun.study.batchJob.repository;

import org.haijun.study.batchJob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Collection;
import java.util.List;

/**
 * 用户实体Bean
 */
public interface UserRepositoryCustom {


    /**
     * 最后创建的用户
     * @return
     */
    User lastCreateUser();
}
