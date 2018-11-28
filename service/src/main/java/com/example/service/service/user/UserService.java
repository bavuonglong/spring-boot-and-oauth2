package com.example.service.service.user;


import com.example.service.dao.User;

public interface UserService {
    User findByUsername(String username);

    User saveOrUpdate(User user);
}
