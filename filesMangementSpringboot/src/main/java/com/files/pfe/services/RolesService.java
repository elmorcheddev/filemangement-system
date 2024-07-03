package com.files.pfe.services;

import java.util.List;

import com.files.pfe.model.Roles;
 
public interface RolesService  {
	List<Roles> listRoles();
	
	Roles addNewUtilisateur(Roles roles);
	
	Roles findBYiDRoles(Long id);
	
	void deleteByIdUtilisateur(Long id);
}
