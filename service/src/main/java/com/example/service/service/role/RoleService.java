package com.example.service.service.role;

import com.example.service.dao.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
