package com.dev.test182.service.impl;

import com.dev.test182.model.Role;
import com.dev.test182.repository.RoleRepository;
import com.dev.test182.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }
}
