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

		@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
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

	@EnableWebSecurity
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService myUserDetailsService;
		@Autowired
		private JwtRequestFilter jwtRequestFilter;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(myUserDetailsService);
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.csrf().disable()
					.authorizeRequests()
					.antMatchers(
							"/bootstrap/**",
							"/fonts/**",
							"/js/**",
							"/css/**",
							"/img/**").permitAll()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/register").permitAll()
					.antMatchers("/h2-console/**").permitAll()
					.anyRequest().authenticated().and().
					exceptionHandling().and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			httpSecurity.headers().frameOptions().disable();
			httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		}

	}

}
