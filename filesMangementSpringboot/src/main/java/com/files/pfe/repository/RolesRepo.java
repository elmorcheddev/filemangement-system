package com.files.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.pfe.model.Roles;

import java.util.List;

 
public interface RolesRepo extends JpaRepository<Roles, Long> {
	
	Roles findByNomRoles(String nomRoles);
}
