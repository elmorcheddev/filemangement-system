package com.files.pfe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.pfe.model.Categorie;
import com.files.pfe.services.CategorieService;

@RestController
@RequestMapping(value = "/api/cat")
public class CategorieController {

	@Autowired
	private CategorieService categorieService;
	@GetMapping(value = "/all")
	public List<Categorie> listCategories(){
		return categorieService.listCategorie();
				
	}
	@PostMapping(value = "/save")
	public Categorie saveCategorie(@RequestBody Categorie categorie) {
		return categorieService.addNewCategorie(categorie);
	}
	@GetMapping(value = "/findById/{idCat}")
	public Categorie findById(@PathVariable("idCat") Long  id) {
		return categorieService.findBYiDCategorie(id);
	}
	@GetMapping(value = "/findByName/{nomCat}")
	public Categorie findByNomCat(@PathVariable("nomCat") String  nomCat) {
		return categorieService.findByNomCat(nomCat);
	}
	@GetMapping(value = "/deleteByID/{idCat}")
	public void deleteById(@PathVariable("idCat") Long  id) {
		 categorieService.deleteByIdCat(id);
		 System.out.println("Deleted");
	}
}
