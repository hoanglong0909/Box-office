package com.cg.bo.service;


import com.cg.bo.model.security.User;
import com.cg.bo.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Iterable<User> findAll();

    User createUser(User user);

    Optional<User> findUserById(Long id);

    UserPrincipal findByUsername(String username);

    boolean isContainUsername(String username);

    boolean isContainPhone(String phone);

    boolean isContainEmail(String email);

    Iterable<User> findAllByDeletedFalse();

    Iterable<User> findAllByDeletedTrue();

    User save(User user);

    User findByName(String username);

    void remove(Long id);

    Iterable<User> findByUserRoleStaff(Long id);
}