package com.txdk.chitter.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.txdk.chitter.entity.Role;
import com.txdk.chitter.entity.User;
import com.txdk.chitter.exception.DuplicateResourceException;
import com.txdk.chitter.exception.EntityNotFoundException;
import com.txdk.chitter.repository.UserRepository;
import com.txdk.chitter.security.AuthenticationResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User request) {
        if (!userRepository.existsByUsername(request.getUsername())) {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            
            userRepository.save(user);

            String token = jwtService.generateToken(user);

            return new AuthenticationResponse(request.getUsername(), Role.USER, token);
        }
        else throw new DuplicateResourceException(request.getUsername(), User.class);
    }

    public AuthenticationResponse authenticate(User request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));

            User user = unwrapUser(userRepository.findByUsername(request.getUsername()), request.getUsername());
            String token = jwtService.generateToken(user);

            return new AuthenticationResponse(user.getUsername(), user.getRole(), token);
        }
        else throw new EntityNotFoundException(request.getUsername(), User.class);

    }

    static User unwrapUser(Optional<User> entity, String username) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(username, User.class);
    }
    
}
