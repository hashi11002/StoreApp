package com.StoreBackend.StoreMain.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityconfig {

    private CustomUserDetailsService userDetailsService;

    @Autowired
    public Securityconfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/signup.html/**").permitAll()
                .anyRequest().permitAll();

        return http.build();
    }

    // @Bean
    // public UserDetailsService users() {
    // UserDetails admin = User.builder()
    // .username("admin")
    // .password(passwordEncoder().encode("password"))
    // .roles("ADMIN")
    // .build();

    // UserDetails user = User.builder()
    // .username("user")
    // .password(passwordEncoder().encode("password"))
    // .roles("USER")
    // .build();

    // return new InMemoryUserDetailsManager(admin, user);
    // }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
