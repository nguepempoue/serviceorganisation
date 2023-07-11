package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.UserTypeRequest;
import com.b2i.serviceorganisation.service.UserTypeServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class UserTypeBootstrap {

    private final UserTypeServiceImplementation userTypeServiceImplementation;

    public UserTypeBootstrap(UserTypeServiceImplementation userTypeServiceImplementation) {
        this.userTypeServiceImplementation = userTypeServiceImplementation;
    }

    public void seed() throws Exception {
        if(userTypeServiceImplementation.countAllUserTypes() == 0L) {
            userTypeServiceImplementation.createType(new UserTypeRequest("ASSOCIATION", "Association"));
        }
    }
}
