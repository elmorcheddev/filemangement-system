package com.files.pfe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.pfe.model.Autorisation;
import com.files.pfe.model.Fichier;
import com.files.pfe.model.FilePermission;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.services.AutorisationService;
import com.files.pfe.services.FichierService;
import com.files.pfe.services.UtilisateurService;

@RestController
@RequestMapping(value = "/api/autorisation")
public class AutorisationController {
	@Autowired
  AutorisationService autorisationService;
	@Autowired 
	private UtilisateurService utilisateurService;
	@Autowired
	private FichierService fichierService;
	@GetMapping(value = "/grouped")
	public Map<String, List<Autorisation>> getGroupedAutorisations() {
	    List<Autorisation> autorisations = autorisationService.listAutorisations();
	    Map<String, List<Autorisation>> groupedAutorisations = new HashMap<>();
	    for (Autorisation autorisation : autorisations) {
	        String userName = autorisation.getUtilisateurs().getUsername();
 	        if (!groupedAutorisations.containsKey(userName)) {
	            groupedAutorisations.put(userName, new ArrayList<>());
 
	        }
	        groupedAutorisations.get(userName).add(autorisation);
	    }
	    return groupedAutorisations;
	}
	@GetMapping(value = "/all")
	public  List<Autorisation>  allAuto() {
	    
	    return autorisationService.listAutorisations();
	}
 	@PostMapping(value = "{fileId}/permissions/download/")
	public Autorisation grantDownloadPermission(@PathVariable Long fileId, @RequestBody Map<String, Long> requestBody) {
	    Long userId = requestBody.get("userId");
	    if (userId != null) {
	    	return autorisationService.grantAccess(fileId, userId, FilePermission.READ);
 	    } else {
 	        return null;
	    }
	}

     @PostMapping(value = "{fileId}/permissions/delete/")
    public Autorisation grantDeletePermissionPermission(@PathVariable Long fileId, @RequestBody Map<String, Long> requestBody) {
    	 
    	 Long userId = requestBody.get("userId");
 	    if (userId != null) {
 	       return autorisationService.grantAccess(fileId, userId, FilePermission.DELETE);
  	    } else {
  	        return null;
 	    }
    }
     @DeleteMapping(value = "/deleteAutorisation/{id}")
     public void deleteAutorization(@PathVariable Long id) {
  		autorisationService.deleteAutorization(id);
 	}
     @PostMapping(value = "{fileId}/permissions/update/")
     public Autorisation grantupdatePermissionPermission(@PathVariable Long fileId, @RequestBody Map<String, Long> requestBody) {
     	 
     	 Long userId = requestBody.get("userId");
  	    if (userId != null) {
  	       return autorisationService.grantAccess(fileId, userId, FilePermission.WRITE);
   	    } else {
   	        return null;
  	    }
     }
     @PostMapping(value = "{fileId}/permissions/archive/")
    public Autorisation grantArchivePermission(@PathVariable Long fileId, @RequestBody Map<String, Long> requestBody){
    	  
    	 Long userId = requestBody.get("userId");
 	    if (userId != null) {
 	      return  autorisationService.grantAccess(fileId, userId, FilePermission.ARCHIVE);
  	    } else {
  	        return null;
 	    }
    }
     @GetMapping(value = "/byUtilisateur/{id}/andFichier/{idfichier}")
 	public List<Autorisation> findByAndUtilisateurs(@PathVariable Long id ,@PathVariable Long idfichier) {
    	 Utilisateurs utilisateurs= utilisateurService.findBYiDUtilisateurs(id);
    	 Fichier fichier=fichierService.getFihierById(idfichier);
    	 return autorisationService.findByUtilisateursAndFichier(utilisateurs, fichier);
	}
    
}
