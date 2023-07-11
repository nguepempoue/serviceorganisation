package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.FunctionRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.Function;
import com.b2i.serviceorganisation.repository.FunctionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FunctionServiceImplementation implements FuntionService{

    private final FunctionRepository functionRepository;

    public FunctionServiceImplementation(FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }

    @Override
    public ResponseEntity<Object> createFunction(FunctionRequest functionRequest) {
        try {

            // CHECK NAME
            Utils.checkStringValues(functionRequest.getName(), "Function name");

            // NEW Function
            Function function = new Function();
            function.setName(functionRequest.getName());

            // SAVE Function
            return ResponseHandler.generateCreatedResponse("Function created !", functionRepository.save(function));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findAllFunctions() {
        List<Function> functionList = functionRepository.findAll();

        try {

            if(functionList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Functions list", functionList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> updateFunction(Long idFunction, FunctionRequest functionRequest) {
        // GET FUNCTION
        Optional<Function> function = functionRepository.findById(idFunction);

        try {

            return function.map(f -> {

                if(functionRequest.getName() != null) {
                    f.setName(functionRequest.getName() );
                }

                return ResponseHandler.generateOkResponse("Function " + idFunction + " has properly been updated !",
                        functionRepository.save(f));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Function not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> deleteFunction(Long idFunction) {
        // GET function
        Optional<Function> post = functionRepository.findById(idFunction);

        try {

            if(!post.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Function not found !");
            }

            functionRepository.deleteById(idFunction);
            return ResponseHandler.generateOkResponse("Function " + idFunction + " has properly been deleted !", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findFunctionById(Long idFunction) {
        // GET FUNCTION
        Optional<Function> function = functionRepository.findById(idFunction);

        try {

            return function.map(p -> ResponseHandler.generateOkResponse("Function " + idFunction, p))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Function not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public Long countAll() {
        return functionRepository.count();
    }

    @Override
    public Function saveFunction(Function function) {
        return functionRepository.save(function);
    }

}
