package com.files.pfe.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fichier {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String contentType;
	    private double sizeFile;
	    private boolean etat;
	     private Date archivedDate;
	    private Date dateUploaded;
	    @ManyToOne
	    @JoinColumn(name = "idUtilisateur")
	    private Utilisateurs utilisateurs;
	    @ManyToOne
	    @JoinColumn(name = "idcat")
	    @JsonBackReference
	    private Categorie categorie;
	    
	    @Column(name = "photo", columnDefinition="LONGBLOB")	    
	    private byte[] data;
		 
		
}
