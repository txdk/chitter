package com.txdk.chitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txdk.chitter.ApplicationConstants;
import com.txdk.chitter.entity.User;
import com.txdk.chitter.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://#{'${server.address}'}" + ApplicationConstants.FRONTEND_SERVER_PORT)
@AllArgsConstructor
@RequestMapping(ApplicationConstants.USER_ENDPOINT_PREFIX)
public class UserController {

    private UserService userService;

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseEntity<String> findUserById(@PathVariable Long id) {
        logger.info("GET request received on endpoint {}/{}", ApplicationConstants.USER_ENDPOINT_PREFIX, id);
        return new ResponseEntity<>(userService.getUser(id).getUsername(), HttpStatus.OK);
    }

    @PostMapping(ApplicationConstants.REGISTRATION_ENDPOINT)
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user) {
        logger.info("POST request received on endpoint {}/register", ApplicationConstants.USER_ENDPOINT_PREFIX);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
