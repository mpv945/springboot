package org.haijun.study.batchJob.repository;

import org.haijun.study.batchJob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Collection;
import java.util.List;

/**
 * 用户实体Bean
 */
public interface UserRepository extends UserRepositoryCustom,JpaRepository<User,String>,QuerydslPredicateExecutor<User> {

    List<User> findByName(String name);

    List<User> findByIdIn(Collection<String> ids);

    List<User> findByNameLike(String name);
}
