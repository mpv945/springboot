package org.haijun.study.batchJob.repository;

import org.haijun.study.batchJob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * 用户实体Bean
 */
public interface UserRepository extends JpaRepository<User,String> {

    List<User> findByName(String name);

    List<User> findByIdIn(Collection<String> ids);

    List<User> findByNameLike(String name);
}
