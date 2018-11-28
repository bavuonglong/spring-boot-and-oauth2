package com.example.service.service.security;

import com.example.service.dao.Role;
import com.example.service.dao.User;
import com.example.service.dao.UserHistory;
import com.example.service.service.user.UserService;
import com.example.service.service.userhistory.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    private UserHistoryService userHistoryService;

    public UserDetailsServiceImpl(UserService userService, UserHistoryService userHistoryService) {
        this.userService = userService;
        this.userHistoryService = userHistoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        List<Role> roles = user.getRoles();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role roleName : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName.getRoleName()));
        }

        UserHistory userHistory = new UserHistory();
        userHistory.setUser(user);
        userHistoryService.saveOrUpdate(userHistory);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getEncryptedPassword(), grantedAuthorities);
    }
}
