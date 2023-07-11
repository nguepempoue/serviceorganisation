package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.service.RoleServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RoleController {

    private final RoleServiceImplementation roleServiceImplementation;

    public RoleController(RoleServiceImplementation roleServiceImplementation) {
        this.roleServiceImplementation = roleServiceImplementation;
    }

    @GetMapping
    public ResponseEntity<Object> findAllRoles() {
        return roleServiceImplementation.findAll();
    }
}
