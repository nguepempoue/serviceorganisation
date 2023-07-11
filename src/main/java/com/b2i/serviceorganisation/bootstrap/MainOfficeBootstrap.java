package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.MainOfficeRequest;
import com.b2i.serviceorganisation.service.MainOfficeServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class MainOfficeBootstrap {

    private final MainOfficeServiceImplementation implementation;

    public MainOfficeBootstrap(MainOfficeServiceImplementation implementation) {
        this.implementation = implementation;
    }

    public void seed() {

        MainOfficeRequest o = new MainOfficeRequest();
        o.setName("BUREAU CENTRAL - MUTUELLE KUNTA");
        implementation.createMainOffice(o);
    }
}
