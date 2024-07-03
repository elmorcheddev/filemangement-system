package com.files.pfe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.files.pfe.model.Autorisation;
import com.files.pfe.model.Categorie;
import com.files.pfe.model.Fichier;
import com.files.pfe.repository.AutorisationRepo;
import com.files.pfe.repository.CategorieRepo;
import com.files.pfe.repository.FichierRepo;
import com.files.pfe.services.CategorieService;
@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private CategorieRepo categorieRepo;
	@Autowired
	private FichierRepo fichierRepo;
	@Autowired
	private AutorisationRepo autorisationRepo;
	@Override
	public List<Categorie> listCategorie() {
		// TODO Auto-generated method stub
		return categorieRepo.findAll();
	}

	@Override
	public Categorie addNewCategorie(Categorie categorie) {
		if(StringUtils.hasLength(categorie.getNomCat()) && categorieRepo.findByNomCat(categorie.getNomCat())==null) {
			System.out.println("CATEGORY AJOUTER AVEC SUCCESS");
			return categorieRepo.save(categorie);
		}else {
			System.out.println("categorie Exist ou bien nom de category est vide");

			return null;
		}
 	}

	@Override
	public Categorie findBYiDCategorie(Long id) {
		return categorieRepo.findById(id).get();
	}

	@Override
	public void deleteByIdCat(Long id) {
	    Categorie categorie = categorieRepo.findById(id).orElse(null);
	    if (categorie != null) {
	        List<Fichier> fichiers = categorie.getFichiers();
	        for (Fichier fichier : fichiers) {
	            List<Autorisation> autorisations = autorisationRepo.findByFichier(fichier);
	            autorisationRepo.deleteAll(autorisations);
	        }
	        fichierRepo.deleteAll(fichiers);
	        categorieRepo.deleteById(id);
	    }
	}


	@Override
	public Categorie findByNomCat(String nomCat) {

		return categorieRepo.findByNomCat(nomCat);
	}

}
