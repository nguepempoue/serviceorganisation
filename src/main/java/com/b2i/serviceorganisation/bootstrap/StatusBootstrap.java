package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.StatusRequest;
import com.b2i.serviceorganisation.service.StatusServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class StatusBootstrap {

    private final StatusServiceImplementation statusServiceImplementation;

    public StatusBootstrap(StatusServiceImplementation statusServiceImplementation) {
        this.statusServiceImplementation = statusServiceImplementation;
    }

    public void seed() {

        if(statusServiceImplementation.countAllStatus() == 0L) {
            statusServiceImplementation.createStatus(new StatusRequest("NORMAL"));
            statusServiceImplementation.createStatus(new StatusRequest("A SURVEILLER"));
            statusServiceImplementation.createStatus(new StatusRequest("SUSPENDU"));
        }
    }
}
