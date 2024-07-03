package com.files.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.pfe.model.Autorisation;
import com.files.pfe.model.FilePermission;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.model.Fichier;
import java.util.List;


 
public interface AutorisationRepo extends JpaRepository<Autorisation, Long> {
	void deleteByFichierAndUtilisateurs(Fichier fichier, Utilisateurs utilisateurs);
 	boolean existsByFichierAndUtilisateursAndPermission(Fichier fichier, Utilisateurs utilisateurs, FilePermission permission);
 	List<Autorisation> findByUtilisateursAndFichier(Utilisateurs utilisateurs, Fichier fichier);
 	List<Autorisation> findByFichierAndUtilisateursAndPermission(Fichier fichier, Utilisateurs utilisateurs, FilePermission permission);
 	List<Autorisation> findByFichier(Fichier fichier);
}
