package com.txdk.chitter.service;

import com.txdk.chitter.entity.User;

public interface UserService {
    
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);

}
