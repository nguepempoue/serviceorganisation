package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.model.Role;
import com.b2i.serviceorganisation.service.RoleServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class RoleBootstrap {

    private final RoleServiceImplementation roleServiceImplementation;

    public RoleBootstrap(RoleServiceImplementation roleServiceImplementation) {
        this.roleServiceImplementation = roleServiceImplementation;
    }

    // ROLES
    public void seed() {
        if(roleServiceImplementation.countAllRoles() == 0)
        {
            roleServiceImplementation.createRole(new Role("ACTUATOR", "ACTUATOR"));
            roleServiceImplementation.createRole(new Role("ADMIN", "ADMIN"));
            roleServiceImplementation.createRole(new Role("MEMBER", "MEMBER"));
            roleServiceImplementation.createRole(new Role("MUTUALIST", "MUTUALIST"));
            roleServiceImplementation.createRole(new Role("OPERATOR", "OPERATOR"));
        }
    }
}
