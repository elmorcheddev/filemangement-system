import { Component } from '@angular/core';
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
  selector: 'app-list-autorisation',
  templateUrl: './list-autorisation.component.html',
  styleUrls: ['./list-autorisation.component.css']
})
export class ListAutorisationComponent {
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
  fichier:Fichier={
    id: 0,
    data: '',
    name: '',
    contentType: '',
    sizeFile: 0,
    etat: false,
    categorie: new Cat,
    dateUploaded: '',
    utilisateurs: new Utilisateur,
    archivedDate: ''
  }
  nomRoles: string;
   listFichier: Fichier[];
  active: boolean;
  autoByUtilisateur: Autorisation[];
  listAuto:  Autorisation[] 
  groupedAutorisations: { [key: string]: Autorisation[] } = {};

fileName: any;
userName:any
  constructor(private autoServie:AutorisationServices,public fichierService:FichierService,private adminService:AdminService,  private catService:CatServices,private authAdmin:AdminAuthService, private router:Router 
     ,private route:ActivatedRoute){}
  ngOnInit(): void {
     if(this.authAdmin.isLoggedIn()){
      this.adminService.getUserInformation().subscribe((data:Utilisateur)=>{
        console.log(data)
       this.utilisateur=data
       this.nomRoles=this.utilisateur.roles[0].nomRoles
      })
    }
    this.getAllFile()
    this.getAllAutorisation()
}
getAllFile() {
  this.fichierService.getFileActive().subscribe((data: Fichier[]) => {
    this.listFichier = data
    console.log(data)
    
  })
}


getAllAutorisation() {
  this.autoServie.getGroupedAutorisations().subscribe(
    (data: { [key: string]: Autorisation[] }) => {
      console.log(data);
      for (let username in data ) {
           if (data.hasOwnProperty(username)) {
            this.groupedAutorisations[username ] = data[username];
           }
        }     
      
    }
  );
}
deleteAutorisation(id:number){
  console.log(id)
  this.autoServie.deleteAutorization(id).subscribe((data)=>{
    console.group(data)
    if(data == null){
      window.alert('Autorisation deleted with suucess')
      this.router.navigate(['/autorisation']) .then(()=>{
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
}
