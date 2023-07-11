package com.b2i.serviceorganisation.bootstrap;

import com.b2i.serviceorganisation.dto.request.PieceTypeRequest;
import com.b2i.serviceorganisation.service.PieceTypeServiceImplementation;
import org.springframework.stereotype.Component;

@Component
public class PieceTypeBootstrap {

    private final PieceTypeServiceImplementation pieceTypeServiceImplementation;

    public PieceTypeBootstrap(PieceTypeServiceImplementation pieceTypeServiceImplementation) {
        this.pieceTypeServiceImplementation = pieceTypeServiceImplementation;
    }

    public void seed() {

        if(pieceTypeServiceImplementation.countAll() == 0L) {

            pieceTypeServiceImplementation.createPieceType(new PieceTypeRequest("EXTRAIT DE NAISSANCE"));
            pieceTypeServiceImplementation.createPieceType(new PieceTypeRequest("CARTE D'IDENTITÉ"));
            pieceTypeServiceImplementation.createPieceType(new PieceTypeRequest("PASSEPORT"));
        }
    }
}
