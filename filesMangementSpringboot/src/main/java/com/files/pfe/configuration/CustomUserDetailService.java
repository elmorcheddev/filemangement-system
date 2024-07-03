package com.files.pfe.configuration;

 import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.files.pfe.model.Utilisateurs;
import com.files.pfe.repository.UtilisateurRepo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UtilisateurRepo utilisateurRepo;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			Utilisateurs utilisateur= utilisateurRepo.findByUsername(username);
			if(utilisateur == null) {
				throw new UsernameNotFoundException("utilisateur n'exist pas "+username);
			}
			return new User(utilisateur.getUsername(),utilisateur.getPassword(),utilisateur.getRoles(). stream()
	                .map(role ->
	                new SimpleGrantedAuthority("ROLE_"+role.getNomRoles()))
	                  .collect(Collectors
	                     .toList()));
		
		}
}
