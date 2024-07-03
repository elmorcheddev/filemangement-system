package com.files.pfe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.pfe.Utils.JwtUtils;
import com.files.pfe.configuration.CustomUserDetailService;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.model.auth.RequestToken;
import com.files.pfe.model.auth.ResponseToken;
import com.files.pfe.repository.UtilisateurRepo;
import com.files.pfe.services.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UtilisateurRepo utilisateurRepo;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Autowired
	private JwtUtils jwtUtil;
	
	
	@GetMapping("/getConnectedUser")
	public ResponseEntity<Utilisateurs> getConnectedUser(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader("Authorization");

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7); // Remove "Bearer " prefix

			String username = jwtUtil.extractUsername(token);
			Utilisateurs utilisateurs = utilisateurService.findByUsername(username);

			return ResponseEntity.ok(utilisateurs);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseToken> authenticate(@RequestBody RequestToken request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final Utilisateurs user = utilisateurRepo.findByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(ResponseToken.builder().token(jwt).utilisateur(user).build());
	}

}
