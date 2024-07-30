package com.StoreBackend.StoreMain.Controller;

import com.StoreBackend.StoreMain.Service.LoginService;
import com.StoreBackend.StoreMain.Service.SignupService;
import com.StoreBackend.StoreMain.DTO.AuthResponseDTO;
import com.StoreBackend.StoreMain.DTO.loginDTO;
import com.StoreBackend.StoreMain.DTO.signupDTO;
import com.StoreBackend.StoreMain.Model.Roles;
import com.StoreBackend.StoreMain.Model.Users;
import com.StoreBackend.StoreMain.Respository.RoleRepository;
import com.StoreBackend.StoreMain.Respository.UserRepository;
import com.StoreBackend.StoreMain.Security.JWTGenerator;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private SignupService signupservice;
    private LoginService loginService;
    private JWTGenerator jwtGenerator;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,
            UserRepository userRepository, JWTGenerator jwtGenerator,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, SignupService signupService,
            LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.signupservice = signupService;
        this.loginService = loginService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signup(@RequestBody Users user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        Users userEntity = new Users();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPostalCode(user.getPostalCode());
        userEntity.setAddress(user.getAddress());
        userEntity.setCity(user.getCity());
        userEntity.setCountry(user.getCountry());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Roles> optionalRole = roleRepository.findByName("USER");
        if (!optionalRole.isPresent()) {
            return new ResponseEntity<>("Role not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Roles roles = optionalRole.get();
        userEntity.setRole(Collections.singletonList(roles));

        try {
            signupservice.createUser(userEntity);
        } catch (Exception e) {
            return new ResponseEntity<>("User registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User registered", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody loginDTO login) {
        String token = loginService.authenticateUser(login);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
