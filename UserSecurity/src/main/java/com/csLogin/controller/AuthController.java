package com.csLogin.controller;

import com.csLogin.model.AuthenticationRequest;
import com.csLogin.model.AuthenticationResponse;
import com.csLogin.model.UserModel;
import com.csLogin.repository.UserRepository;
import com.csLogin.services.UserServices;
import com.csLogin.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServices userServices;

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/welcome")
	private String testingToken() {
		return "Welcome to DASHBOARD " + SecurityContextHolder.getContext().getAuthentication().getName();
	}

	// to add new user
	@PostMapping("/authreg")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		String username = authenticationRequest.getEmail();
		String password = authenticationRequest.getPassword();
		UserModel userModel = new UserModel();
		userModel.setEmail(username);
		userModel.setPassword(password);
		try {
			userRepository.save(userModel);
		} catch (Exception e) {
			throw new Exception("Invalid", e);
		}
		return ResponseEntity.ok(new AuthenticationResponse("Successful subscription for client " + username));

	}

	// to authenticate existing user
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		String username = authenticationRequest.getEmail();
		String password = authenticationRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new Exception("Invalid", e);

		}
		UserDetails loadeduser = userServices.loadUserByUsername(username);
		String generatedToken = jwtUtils.generateToken(loadeduser);

		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));

	}

}
