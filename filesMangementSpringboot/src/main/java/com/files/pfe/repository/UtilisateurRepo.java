package com.files.pfe.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.pfe.model.Roles;
import com.files.pfe.model.Utilisateurs;

public interface UtilisateurRepo extends JpaRepository<Utilisateurs, Long> {
List<Utilisateurs> findByEtatIsFalse();
List<Utilisateurs> findByEtatIsTrue();
	Utilisateurs findByUsername(String username);
 	boolean existsByRolesContains(Set<Roles> singleton);
 	boolean existsByUsername(String username);
}
