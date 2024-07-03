package com.files.pfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.files.pfe.model.Categorie;
import com.files.pfe.model.Fichier;
  
public interface FichierRepo extends JpaRepository<Fichier, Long> {
	List<Fichier> findByCategorie(Categorie categorie);
    List<Fichier> findAllByEtatIsTrueAndCategorie(Categorie categorie);
    List<Fichier> findAllByEtatIsFalseAndCategorie(Categorie categorie);
    List<Fichier> findAllByEtatIsTrue();
    List<Fichier> findAllByEtatIsFalse();
    List<Fichier> findTop10ByOrderByDateUploadedDesc();

    void deleteByCategorie(Categorie categorie);
    @Query(value="SELECT DATE(date_uploaded) AS upload_date, COUNT(*) AS upload_count FROM files.fichier GROUP BY upload_date;",nativeQuery = true)
    List<Object[]> findUploadCountByDate();
}
