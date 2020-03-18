package com.dev.test182.service;

import com.dev.test182.model.Role;

public interface RoleService {
    Role save(Role role);

    Role getByName(String roleName);
}
