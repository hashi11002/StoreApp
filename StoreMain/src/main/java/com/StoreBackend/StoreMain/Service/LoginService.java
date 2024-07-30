package com.StoreBackend.StoreMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.StoreBackend.StoreMain.DTO.loginDTO;
import com.StoreBackend.StoreMain.Respository.UserRepository;
import com.StoreBackend.StoreMain.Security.JWTGenerator;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    public String authenticateUser(loginDTO login) {
        Authentication authenticated = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        String token = jwtGenerator.generateToken(authenticated);
        return token;
    }
}
