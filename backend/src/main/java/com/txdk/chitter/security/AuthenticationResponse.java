package com.txdk.chitter.security;

import com.txdk.chitter.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String username;
    private Role role;
    private String token;
    
}
