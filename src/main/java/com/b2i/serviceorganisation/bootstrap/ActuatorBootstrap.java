package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.model.Role;
import com.b2i.serviceorganisation.model.User;
import com.b2i.serviceorganisation.service.RoleServiceImplementation;
import com.b2i.serviceorganisation.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ActuatorBootstrap {

    private final UserServiceImplementation userServiceImplementation;

    private final RoleServiceImplementation roleServiceImplementation;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ActuatorBootstrap(UserServiceImplementation userServiceImplementation, RoleServiceImplementation roleServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
        this.roleServiceImplementation = roleServiceImplementation;
    }

    // ACTUATOR
    public void seed() {
        if(userServiceImplementation.countAllUsers() == 0) {

            // USER & ROLE
            User user = new User();
            Role actuator = roleServiceImplementation.findRoleByName("ACTUATOR");
            Role admin = roleServiceImplementation.findRoleByName("ADMIN");

            // SETTING VALUES ACTUATOR
            user.setFirstName("B2I");
            user.setLastName("B2I");
            user.setUserName("actuator");
            user.setPassword(passwordEncoder.encode("open"));
            //user.setPassword("open");
            user.setPhoneNumber("+22522403750");
            user.setEmail("contact@b2i-solutions.com");
            user.getRoles().add(actuator);

            // SAVING
            userServiceImplementation.saveUser(user);

            // SETTING VALUES ADMIN
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("123456"));
            //user.setPassword("123456");
            user.setPhoneNumber("+22522403750");
            user.setEmail("admin@gmail.com");
            user.getRoles().add(admin);

            // SAVING
            userServiceImplementation.saveUser(user);
        }
    }
}
