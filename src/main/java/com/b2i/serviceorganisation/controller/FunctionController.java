package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.FunctionRequest;
import com.b2i.serviceorganisation.service.FunctionServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/functions")
@CrossOrigin("*")
public class FunctionController {

    private final FunctionServiceImplementation functionServiceImplementation;

    public FunctionController(FunctionServiceImplementation functionServiceImplementation) {
        this.functionServiceImplementation = functionServiceImplementation;
    }


    // CREATE
    @PostMapping
    public ResponseEntity<Object> createFunction(@RequestBody FunctionRequest functionRequest) {
        return functionServiceImplementation.createFunction(functionRequest);
    }


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllFunctions() {
        return functionServiceImplementation.findAllFunctions();
    }


    // UPDATE POST
    @PutMapping("/{idFunction}")
    public ResponseEntity<Object> updateFunction(@PathVariable("idFunction") Long idFunction, @RequestBody FunctionRequest functionRequest) {
        return functionServiceImplementation.updateFunction(idFunction, functionRequest);
    }


    // DELETE POST
    @DeleteMapping("/{idFunction}")
    public ResponseEntity<Object> deleteFunction(@PathVariable("idFunction") Long idFunction) {
        return functionServiceImplementation.deleteFunction(idFunction);
    }


    // FIND POST BY ID
    @GetMapping("/{idFunction}")
    public ResponseEntity<Object> findFunctionById(@PathVariable("idFunction") Long idFunction) {
        return functionServiceImplementation.findFunctionById(idFunction);
    }
}
