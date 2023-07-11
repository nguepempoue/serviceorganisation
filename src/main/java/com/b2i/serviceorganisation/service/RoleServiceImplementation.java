package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Role;
import com.b2i.serviceorganisation.repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    // CREATE ROLE
    @Override
    public Role createRole(Role role) {

        // NEW ROLE
        Role r = new Role();

        try {
            // SETTING VALUES
            r.setLabel(role.getLabel());
            r.setName(role.getName());
            r = roleRepository.save(r);
            return r;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // GETTING ALL ROLES
    @Override
    public List<Role> findAllRoles() {

        try {
            // GETTING ALL ROLES
            List<Role> roles = findAllRoles();
            if(roles.isEmpty())
            {
                return null;
            }
            return roles;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        try {
            // GETTING ALL ROLES
            List<Role> roles = this.roleRepository.findAll();
            if(roles.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Roles list", roles);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE ROLE
    @Override
    public Role updateRole(Long id, Role role) {

        // GETTING ROLE
        Optional<Role> r = roleRepository.findById(id);
        Role r2 = new Role();
        try {
            if(!r.isPresent()) {
                System.out.println("Role not found !");
                return null;
            }
            // SETTING VALUES
            r2 = r.get();
            r2.setLabel(role.getLabel());
            r2.setName(role.getLabel());
            r2 = roleRepository.save(r2);
            return r2;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // DELETE ROLE
    @Override
    public Boolean deleteRoleById(Long id) {

        // GETTING ROLE
        Optional<Role> role = roleRepository.findById(id);
        try {
            if(!role.isPresent()) {
                System.out.println("Role not found !");
                return false;
            }
            // SETTING VALUES
            roleRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // FIND ROLE BY NAME
    @Override
    public Role findRoleByName(String name) {

        // GET ROLE
        Optional<Role> role = roleRepository.findRoleByName(name);
        try {
            if(!role.isPresent()) {
                System.out.println("Role not found !");
                return null;
            }
            return role.get();
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // FIND ROLE BY ID
    @Override
    public Role findRoleById(Long id) {

        // GET ROLE
        Optional<Role> role = roleRepository.findById(id);
        try {
            if(!role.isPresent()) {
                System.out.println("Role not found !");
                return null;
            }
            return role.get();
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // COUNT ALL ROLES
    @Override
    public Long countAllRoles() {
        return roleRepository.count();
    }
}
