import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
 import { Fichier } from '../../monClass/fichier';
import { Utilisateur } from '../../monClass/utilisateur';
import { Autorisation } from '../../monClass/Autorisation';
import { Cat } from '../../monClass/categorie';
import { AutorisationServices } from '../../monService/Autorisation.services';
import { FichierService } from '../../monService/fichier.service';
import { AdminService } from '../../monService/admin.service';
import { CatServices } from '../../monService/cat.service';
import { AdminAuthService } from '../../monService/admin-auth.service';
import { UtilisateurService } from '../../monService/utilisateur.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  utilisateur: Utilisateur={
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    cin: '',
    username: '',
    password: '',
    roles: [],
    autorisations: [],
    etat: false,
    photo: ''
  };
  roles: any;
  nomRoles: string;
  view(nomCat: string) {
    this.router.navigate(['/sousFolder',{nomCat}])
     }
     constructor(private catService:CatServices,private adminService:AdminService
        ,private authAdmin:AdminAuthService, private router:Router , private activatedRoute:ActivatedRoute){}

  folders: Cat[];
  ngOnInit() {
      
        if(this.authAdmin.isLoggedIn()){
          this.adminService.getUserInformation().subscribe((data:any)=>{
            console.log(data)
           this.utilisateur=data
           this.nomRoles=this.utilisateur.roles[0].nomRoles
          });} 
    
  
  
  }
  
 loginOrNot(){
	return this.authAdmin.isLoggedIn();
}
logout(){
  this.router.navigate(['/login'])
  return this.authAdmin.clear()
}
 
  }
 
 
 


