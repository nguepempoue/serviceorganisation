package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.Utils.JwtUtil;
import com.b2i.serviceorganisation.dto.request.LoginRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.repository.UserRepository;
import com.b2i.serviceorganisation.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImplementation userServiceImplementation;


    // LOGIN
/*     @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception(e);
        }
        return jwtUtil.generateToken(loginRequest.getEmail());
    }*/

     @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return ResponseHandler.generateResponse("Authentication token", HttpStatus.OK, jwtUtil.generateToken(loginRequest.getEmail()));
        }
        catch (Exception e) {
            throw new Exception(e);
        }

    }


    // SIGN UP
    /* @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
        return userServiceImplementation.createUserMember(userRequest);
    } */
}
