package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.FunctionRequest;
import com.b2i.serviceorganisation.dto.request.PostRequest;
import com.b2i.serviceorganisation.service.FunctionServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class FunctionBootstrap {

    private final FunctionServiceImplementation functionServiceImplementation;

    public FunctionBootstrap(FunctionServiceImplementation functionServiceImplementation) {
        this.functionServiceImplementation = functionServiceImplementation;
    }

    public void seed() {

        if(functionServiceImplementation.countAll() == 0L) {

            functionServiceImplementation.createFunction(new FunctionRequest("COORDONNATEUR"));
            functionServiceImplementation.createFunction(new FunctionRequest("COORDONNATEUR ADJOINT"));
            functionServiceImplementation.createFunction(new FunctionRequest("RAPPORTEUR"));
            functionServiceImplementation.createFunction(new FunctionRequest("RAPPORTEUR SUPPLEANT"));
            functionServiceImplementation.createFunction(new FunctionRequest("MEMBRE"));
        }
    }
}
