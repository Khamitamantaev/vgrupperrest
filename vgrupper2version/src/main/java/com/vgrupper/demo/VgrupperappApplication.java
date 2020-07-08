package com.vgrupper.demo;

import com.vgrupper.demo.entity.User;
import com.vgrupper.demo.filters.JwtRequestFilter;
import com.vgrupper.demo.models.AuthenticationRequest;
import com.vgrupper.demo.models.AuthenticationResponse;
import com.vgrupper.demo.repositories.UserRepository;
import com.vgrupper.demo.service.MyUserDetailsService;
import com.vgrupper.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SpringBootApplication
public class VgrupperappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VgrupperappApplication.class, args);
	}


}
