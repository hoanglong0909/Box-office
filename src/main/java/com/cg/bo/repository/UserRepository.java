package com.cg.bo.repository;

import com.cg.bo.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    Iterable<User> findAllByDeletedFalse();

    Iterable<User> findAllByDeletedTrue();

    @Query(nativeQuery = true,value = "select * from users where role_id = ?")
    Iterable<User> findByUserRoleStaff(Long id);
}