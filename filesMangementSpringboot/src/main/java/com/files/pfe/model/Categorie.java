
package com.files.pfe.model;

 
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nomCat;
		@OneToMany( mappedBy = "categorie",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JsonManagedReference
		private List<Fichier> fichiers;

}
