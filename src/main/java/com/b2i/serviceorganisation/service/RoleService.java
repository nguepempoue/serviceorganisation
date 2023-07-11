package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {

    // CRUD OPERATIONS //
    Role createRole(Role role);

    List<Role> findAllRoles();

    ResponseEntity<Object> findAll();

    Role updateRole(Long id, Role role);

    Boolean deleteRoleById(Long id);

    // MORE //
    Role findRoleByName(String name);

    Role findRoleById(Long id);

    Long countAllRoles();
}
