package com.b2i.serviceorganisation.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    // GENERATE RESPONSE
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object response) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", response);

        return new ResponseEntity<Object>(map, status);
    }

    // GENERATE RESPONSE FOR EXCEPTION
    public static ResponseEntity<Object> generateError(Exception e) {
        return ResponseHandler.generateResponse("Error : " + e.getMessage(), HttpStatus.MULTI_STATUS, null);
    }

    // GENERATE RESPONSE FOR NO_CONTENT
    public static ResponseEntity<Object> generateNoContentResponse(String message) {
        return ResponseHandler.generateResponse(message, HttpStatus.NO_CONTENT, null);
    }

    // GENERATE RESPONSE FOR NOT_FOUND
    public static ResponseEntity<Object> generateNotFoundResponse(String message) {
        return ResponseHandler.generateResponse(message, HttpStatus.NOT_FOUND, null);
    }

    // GENERATE OK RESPONSE
    public static ResponseEntity<Object> generateOkResponse(String message, Object responseObj) {
        return generateResponse(message, HttpStatus.OK, responseObj);
    }

    // GENERATE CREATED RESPONSE
    public static ResponseEntity<Object> generateCreatedResponse(String message, Object responseObj) {
        return generateResponse(message, HttpStatus.CREATED, responseObj);
    }
}
