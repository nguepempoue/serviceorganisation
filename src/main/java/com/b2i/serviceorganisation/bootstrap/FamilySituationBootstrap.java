package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.FamilySituationRequest;
import com.b2i.serviceorganisation.service.FamilySituationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FamilySituationBootstrap {

    @Autowired
    private FamilySituationServiceImplementation familySituationServiceImplementation;

    public void seed() {

        if(familySituationServiceImplementation.countAll() == 0L) {

            familySituationServiceImplementation.createFamilySituation(new FamilySituationRequest("CÉLIBATAIRE"));
            familySituationServiceImplementation.createFamilySituation(new FamilySituationRequest("MARIÉ(E)"));
            familySituationServiceImplementation.createFamilySituation(new FamilySituationRequest("DIVORCÉ(E)"));
            familySituationServiceImplementation.createFamilySituation(new FamilySituationRequest("VEUF(VE)"));
        }
    }
}
