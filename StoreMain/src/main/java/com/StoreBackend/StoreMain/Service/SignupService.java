package com.StoreBackend.StoreMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StoreBackend.StoreMain.Model.Users;
import com.StoreBackend.StoreMain.Respository.UserRepository;

@Service
public class SignupService {

    @Autowired
    UserRepository userRepository;

    public void createUser(Users user) {
        userRepository.save(user);
    }
}
