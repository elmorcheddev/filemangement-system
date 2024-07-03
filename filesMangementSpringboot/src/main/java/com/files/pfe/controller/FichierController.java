
package com.files.pfe.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.files.pfe.Utils.JwtUtils;
import com.files.pfe.model.Categorie;
import com.files.pfe.model.Fichier;
import com.files.pfe.model.FilePermission;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.repository.AutorisationRepo;
import com.files.pfe.repository.CategorieRepo;
import com.files.pfe.services.AutorisationService;
import com.files.pfe.services.FichierService;
 import com.files.pfe.services.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RestController
@RequestMapping(value = "/api/file/")
 
public class FichierController {
	 @Autowired
	    private FichierService fichierService;
	 @Autowired
	 private CategorieRepo categorieRepo;

	    @PostMapping(value = "upload" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<Fichier> uploadDocument(@RequestPart("file") MultipartFile file ,
	    		@RequestPart("fichier") Fichier fichier,
	    		@RequestParam("catId") Long sousCategorieId , @RequestParam("userId") Long userId)
	    			throws IOException {
	        try {
	        	fichier.setName(file.getOriginalFilename());
	        	fichier.setSizeFile(file.getSize());
	        	fichier.setContentType(file.getContentType());
	        	 fichier.setData(file.getBytes());
	            return ResponseEntity.ok(fichierService.uploadFichier(fichier, sousCategorieId, userId));
	        } catch (Exception e) {
	             e.printStackTrace();
	             return null;
	        }
	    }
	    @GetMapping(value = "all")
	    public List<Fichier> findAllFichier(){
	    	return fichierService.listFichier();
	    }
	    @GetMapping(value = "all/{id}")
	    public List<Fichier> findAllFichierByEtatAndSousCategory(@PathVariable Long id){
	    	Categorie categorie = categorieRepo.findById(id).get();
	    	return fichierService.findAllByEtatIsTrueAndCategorie(categorie);
	    }
	    @GetMapping(value = "allArchiver/{id}")
	    public List<Fichier> findAllFichierByEtatFalseAndSousCategory(@PathVariable Long id){
	    	Categorie categorie = categorieRepo.findById(id).get();
	    	return fichierService.findAllByEtatIsFalseAndCategorie(categorie);
	    }
	    @GetMapping("/upload-count-by-date")
	    public List<Map<String, Object>> getUploadCountByDate() {
	        return fichierService.getUploadCountByDate();
	    }
	    @GetMapping(value = "findById/{id}")
	    public ResponseEntity<Fichier> findFichierById(@PathVariable Long id) {
	    	 

	         return     ResponseEntity.ok(fichierService.getFihierById(id));
	    }

	    @GetMapping(value = "byId/{id}")
	    public ResponseEntity<byte[]> findById(@PathVariable Long id) {
	        Fichier fichier = fichierService.getFihierById(id);
	        byte[] fileData = fichier.getData();
	        String fileName = fichier.getName();
	        String contentType = fichier.getContentType(); // Retrieve content type from database

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.valueOf(contentType));
	        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());

	        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
	    }


	    @GetMapping(value = "archive/{id}")
	    public ResponseEntity<ResponseEntity<String>> archiverFichier(@PathVariable Long id ) {
	    	 
	    	return   ResponseEntity.ok(fichierService.archiverFichier(id)) ;
	    }
	    @GetMapping(value = "/allActive")
	    public List<Fichier> findAllByEtatIsTrue() {
			return fichierService.findAllByEtatIsTrue();
		}
	    @GetMapping(value = "/allDesactive")
		public List<Fichier> findAllByEtatIsFalse() {
			return fichierService.findAllByEtatIsFalse();
		}
	    
	    @GetMapping(value = "top10")
	 		public List<Fichier> findTop10ByOrderByDateUploadedDesc() {
	 			// TODO Auto-generated method stub
	 			return fichierService.findTop10ByOrderByDateUploadedDesc();
	 		}
 		 
	  
}

