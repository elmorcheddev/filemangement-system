package com.files.pfe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.files.pfe.Utils.JwtUtils;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.model.auth.RequestToken;
import com.files.pfe.services.UtilisateurService;
 
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/utilisateur")
 public class UtilisateurController {
@Autowired
	private UtilisateurService utilisateurService;
 
	public UtilisateurController(UtilisateurService utilisateurService) {
 		this.utilisateurService = utilisateurService;
	}
	 
  
	@GetMapping(value = "/all")
	public List<Utilisateurs> list(){
		return utilisateurService.listUtilisateurs();
	}
	@GetMapping(value = "/allInactive")
	public List<Utilisateurs> findByEtatIsFalse() {
		// TODO Auto-generated method stub
		return utilisateurService.findByEtatIsFalse();
	}

	@GetMapping(value = "/allActive")
	public List<Utilisateurs> findByEtatIsTrue() {
		// TODO Auto-generated method stub
		 return utilisateurService.findByEtatIsTrue();

	}
	@PostMapping(value = "/saveAdmin")
	public Utilisateurs registerAdmin(@RequestPart("admin") Utilisateurs utilisateurs, @RequestPart("photo") MultipartFile image) {
		try {
			utilisateurs.setPhoto(image.getBytes());
			return utilisateurService.addNewADMIN(utilisateurs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@PostMapping(value = "/saveEmployeur")
	public Utilisateurs registerEmployeur(@RequestPart("user") Utilisateurs utilisateurs, @RequestPart("photo") MultipartFile image) {
		try {
			utilisateurs.setPhoto(image.getBytes());
			return utilisateurService.addNewUtilisateur(utilisateurs);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping(value = "/changeEtatUtilisateur/{id}")
	public Utilisateurs changeEtatUtilisateur(@PathVariable Long id) {
	return utilisateurService.activeDesactiveUtilisateur(id);
	}
	@PutMapping(value = "/updateUtilisateur")
	public Utilisateurs updateUtilisateur(@RequestBody Utilisateurs utilisateurs) {
		return utilisateurService.updateUtilisateur(utilisateurs);
	}
	@GetMapping(value = "/byId/{id}")
	public Utilisateurs  findByUtilisateur(@PathVariable("id") Long id){
		return utilisateurService.findBYiDUtilisateurs(id);
	}
	
	  
}
