package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.CivilityRequest;
import com.b2i.serviceorganisation.service.CivilityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CivilityBootstrap {

    @Autowired
    private CivilityServiceImplementation civilityServiceImplementation;

    public void seed() {

        if(civilityServiceImplementation.countAll() == 0L) {

            civilityServiceImplementation.createCivility(new CivilityRequest("MONSIEUR", "Mr"));
            civilityServiceImplementation.createCivility(new CivilityRequest("MADAME", "Mme"));
            civilityServiceImplementation.createCivility(new CivilityRequest("MADEMOISELLE", "Mlle"));
        }
    }
}
