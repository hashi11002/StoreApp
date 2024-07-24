package com.StoreBackend.StoreMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StoreBackend.StoreMain.Respository.UserRepository;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public void authenticateUser(String username, String password) {

    }
}
