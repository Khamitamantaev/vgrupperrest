package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.User;
import com.vgrupper.demo.models.AuthenticationRequest;
import com.vgrupper.demo.models.AuthenticationResponse;
import com.vgrupper.demo.repositories.UserRepository;
import com.vgrupper.demo.service.MyUserDetailsService;
import com.vgrupper.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class HelloWorldController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        return new  ResponseEntity<String>("Your account has been authenticated!" , HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        User usermain = new User();
        usermain.setPassword(authenticationRequest.getPassword());
        usermain.setUsername(authenticationRequest.getUsername());
        userRepository.save(usermain);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new AuthenticationResponse(jwt, usermain));
    }

}