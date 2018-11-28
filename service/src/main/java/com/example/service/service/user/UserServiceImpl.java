package com.example.service.service.user;

import com.example.service.constant.RoleConstant;
import com.example.service.dao.Role;
import com.example.service.dao.User;
import com.example.service.repository.UserRepository;
import com.example.service.service.role.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder userPasswordEncoder;

    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder userPasswordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveOrUpdate(User user) {
        user.setEncryptedPassword(userPasswordEncoder.encode(user.getPassword()));

        Role roleUser = roleService.findByRoleName(RoleConstant.USER.getValue());
        user.setRoles(Collections.singletonList(roleUser));
        return userRepository.save(user);
    }
}
