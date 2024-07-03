import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'; 
import { Utilisateur } from '../../monClass/utilisateur';
import { UtilisateurService } from '../../monService/utilisateur.service';
import { AdminAuthService } from '../../monService/admin-auth.service';
import { AdminService } from '../../monService/admin.service';

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css']
})
export class UtilisateurComponent implements OnInit{

  nomRoles: string;
  employeur: Utilisateur={
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
  image: any;
  message: string;
reset(form: NgForm) {
  form.resetForm()
 }

  listUtilisateur: Utilisateur[];
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
  msg: string;
  ngOnInit(): void {
    if(this.authAdmin.isLoggedIn()){
      this.adminService.getUserInformation().subscribe((data:Utilisateur)=>{
        console.log(data)
       this.utilisateur=data
       this.nomRoles=this.utilisateur.roles[0].nomRoles
      })
    }
    this.getAllUtilisateur()
  }
constructor(private utilisateurService:UtilisateurService ,
    private adminService:AdminService ,private authAdmin:AdminAuthService,private router:Router){}

getAllUtilisateur(){
  this.utilisateurService.getAllUtilisateur().subscribe((data:Utilisateur[])=>{
    this.listUtilisateur=data
  })
}
changeEtat(id: number) {
  this.utilisateurService.changeEtatUtilisateur(id).subscribe((data:Utilisateur)=>{
    if(data != null && data.etat == true){
      window.alert("COMPTE Utilisateur a ete active")
      this.router.navigate(['/employeur']).then(()=>{
        location.reload()
      })
    }
    if(data != null && data.etat == false){
      window.alert("COMPTE Utilisateur a ete Desactive")
      this.router.navigate(['/employeur']).then(()=>{
        location.reload()
      })
    }
  })
}
addNewUtilisateur(form:NgForm){
  const formData = new FormData();
  formData.append('photo', this.image);
  formData.append('user', new Blob([JSON.stringify(this.employeur)], { type: 'application/json' }));
  if (form.valid) {
 this.utilisateurService.saveUtilisateur(formData).subscribe((data:Utilisateur)=>{
  console.log(data)
  if(data!== null){
    window.alert("UTILISATEUR A ETE AJOUTER AVEC SUCCESS")
    this.router.navigate(['/employeur']).then(()=>{
      location.reload()
    })
  }else if(data==null){
    this.message="there is a account Admin exist "
    console.log(this.message)
  }
 })
}else {
  form.control.markAllAsTouched();
}

}
update(id: number) {
this.utilisateurService.getUtilisateur(id).subscribe((data:Utilisateur)=>{
  this.employeur=data
})
}
 updateEmployeur(form:NgForm){
  this.utilisateurService.updateUtilisateur(this.employeur).subscribe((data:Utilisateur)=>{
    console.log(data)
    if(data !== null){
      window.alert("Utilisateur a ete modifier avec suuceess")
      this.router.navigate(['/employeur']).then(()=>{
        location.reload()
      })
    }
  })
 }
public loginOrNot(){
  return this.authAdmin.isLoggedIn();
}
logout() {
  this.router.navigate(['/login'])
  return this.authAdmin.clear()
}
fileSelected(event: any) {
  this.image = event.target.files[0]
  console.log(event.target.files[0]) 
}
}
