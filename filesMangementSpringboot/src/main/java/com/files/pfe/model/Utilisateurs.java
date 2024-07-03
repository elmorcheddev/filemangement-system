package com.files.pfe.model;


import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateurs     {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
@Lob
@Column(name = "photo", columnDefinition="LONGBLOB")	
	private byte[]  photo;
	private String nom;
	private String prenom;
	private String adresse;
	private String cin ;
	private boolean etat;
	private String username;
	private String password;
 	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name= "USER_ROLE",
	      joinColumns = {@JoinColumn(name= "ID_USER")
	      },
	     inverseJoinColumns = {
			@JoinColumn(name = "ID_ROLE")
	})
	private Set<Roles> roles;
 
	
}
