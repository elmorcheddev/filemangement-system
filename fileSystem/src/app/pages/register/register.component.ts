import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'; 
import { UtilisateurService } from '../../monService/utilisateur.service';
import { Utilisateur } from '../../monClass/utilisateur';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  message: string;
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
    etat: true,
    photo: ''
  };
  image: any;
constructor(private utilisateurService:UtilisateurService, private router:Router){}
ngOnInit(): void {
 }
register(form: NgForm) {
  const formData = new FormData();
  formData.append('photo', this.image);
  formData.append('admin', new Blob([JSON.stringify(this.utilisateur)], { type: 'application/json' }));
  if (form.valid) {
 this.utilisateurService.saveAdmin(formData).subscribe((data:Utilisateur)=>{
  console.log(data)
  if(data!== null){
    this.router.navigate(['/login'])
  }else if(data==null){
    this.message="there is a account Admin exist "
    console.log(this.message)
  }
 })
}else {
  form.control.markAllAsTouched();
}
}
fileSelected(event: any) {
  this.image = event.target.files[0]
  console.log(event.target.files[0]) 
}
}
