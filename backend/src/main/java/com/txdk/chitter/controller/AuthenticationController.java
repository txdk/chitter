package com.txdk.chitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.txdk.chitter.ApplicationConstants;
import com.txdk.chitter.entity.User;
import com.txdk.chitter.security.AuthenticationResponse;
import com.txdk.chitter.service.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://#{'${server.address}'}" + ApplicationConstants.FRONTEND_SERVER_PORT)
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping(ApplicationConstants.REGISTRATION_ENDPOINT)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request) {
        logger.info("Request received to register a new user with username {}", request.getUsername());
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.LOGIN_ENDPOINT)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        logger.info("Attempted login for user: {}", request.getUsername());
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }
    
}
