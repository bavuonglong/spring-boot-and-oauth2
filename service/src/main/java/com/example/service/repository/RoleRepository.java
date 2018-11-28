package com.example.service.repository;

import com.example.service.dao.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}
