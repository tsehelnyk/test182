package com.dev.test182.repository;

import com.dev.test182.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRoleName(String roleName);

}
