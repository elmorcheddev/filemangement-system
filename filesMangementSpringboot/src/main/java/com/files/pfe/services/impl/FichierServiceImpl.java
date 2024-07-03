package com.files.pfe.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.files.pfe.model.Categorie;
import com.files.pfe.model.Fichier;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.repository.CategorieRepo;
import com.files.pfe.repository.FichierRepo;
import com.files.pfe.repository.UtilisateurRepo;
import com.files.pfe.services.FichierService;
@Service
public class FichierServiceImpl implements FichierService{
	@Autowired
private FichierRepo fichierRepo;
	@Autowired
	private CategorieRepo categorieRepo;
	@Autowired
	private UtilisateurRepo utilisateurRepo;
	@Override
	public List<Fichier> listFichier() {
		// TODO Auto-generated method stub
		return fichierRepo.findAll();
	}

	@Override
	public Fichier uploadFichier(Fichier fichier, Long sousCategorieId , Long userId) throws IOException {
 	    	Fichier document = new Fichier();
 	     Categorie categorie = categorieRepo.findById(sousCategorieId).get();
 	     Utilisateurs utilisateurs= utilisateurRepo.findById(userId).get();
 	    document.setCategorie(categorie);
	    document.setUtilisateurs(utilisateurs);
        document.setName(fichier.getName());
        document.setSizeFile(fichier.getSizeFile());
        document.setContentType(fichier.getContentType());
        document.setData(fichier.getData());
        document.setArchivedDate(null);
        document.setDateUploaded(new Date());
        document.setSizeFile(fichier.getSizeFile());
        document.setEtat(true);
        System.out.println("File name: " + fichier.getName());
 	    	 
             
 	    	return fichierRepo.save(document);
    }
	@Override
	 public List<Map<String, Object>> getUploadCountByDate() {
	        List<Object[]> data = fichierRepo.findUploadCountByDate();
	        
	        // Convert Object[] to Map<String, Object>
	        return data.stream()
	                .map(objects -> {
	                    Map<String, Object> map = new HashMap<>();
	                    map.put("dateUploaded", objects[0]);
	                    map.put("uploadCount", objects[1]);
	                    return map;
	                })
	                .collect(Collectors.toList());
	    }
@Override
    public Fichier getFihierById(Long id) {
        return fichierRepo.findById(id).get();
    
	}

	 

	@Override
	public void deleteByIdUtilisateur(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseEntity<String> archiverFichier(Long id) {
		Fichier file= fichierRepo.findById(id).get();
		if(file.isEtat()== true) {
			file.setEtat(false);
			file.setArchivedDate(new Date());
			fichierRepo.save(file);
			String res="fichier archive";
			return ResponseEntity.ok(res);
		}else {
			file.setEtat(true);
			file.setArchivedDate(new Date());
			fichierRepo.save(file);
			

			String res="fichier recupere";

			return ResponseEntity.ok(res);

		}
		
		
	}

	@Override
	public List<Fichier> findAllByEtatIsTrueAndCategorie(Categorie categorie) {
 		return fichierRepo.findAllByEtatIsTrueAndCategorie(categorie);
	}
	@Override
	public List<Fichier> findAllByEtatIsFalseAndCategorie(Categorie categorie) {
 		return fichierRepo.findAllByEtatIsFalseAndCategorie(categorie);
	}

	@Override
	public List<Fichier> findAllByEtatIsTrue() {
		// TODO Auto-generated method stub
		return fichierRepo.findAllByEtatIsTrue();
	}

	@Override
	public List<Fichier> findAllByEtatIsFalse() {
		// TODO Auto-generated method stub
		return fichierRepo.findAllByEtatIsFalse();
	}

	@Override
	public List<Fichier> findTop10ByOrderByDateUploadedDesc() {
		// TODO Auto-generated method stub
		return fichierRepo.findTop10ByOrderByDateUploadedDesc();
	}
}
