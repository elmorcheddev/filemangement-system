import { HttpErrorResponse } from '@angular/common/http';
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
  selector: 'app-file-archiver',
  templateUrl: './file-archiver.component.html',
  styleUrls: ['./file-archiver.component.css']
})
export class FileArchiverComponent implements OnInit {
  listUtilisateur: Utilisateur[];
  auto: Autorisation={
    id: 0,
    permission: '',
    fichier: new Fichier,
    utilisateurs: new Utilisateur
  };
  autoByUtilisateur: Autorisation[];
  listAuto: Autorisation[];
 
  message: string;



  cat: Cat = {
    id: 0,
    nomCat: '',
    isExpanded: false,
    fichiers: []
  };
  active: boolean = false


  id: number

  listFichier: Fichier[];
  selectedFile: any;
  listFile: Fichier[];
  utilisateur: Utilisateur = {
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
  nomRoles: string;
  folders: Cat[];
  isExpanded: boolean = false;
  fichier: Fichier={
    id: 0,
    name: '',
    contentType: '',
    sizeFile: 0,
    etat: true,
    categorie: new Cat,
    dateUploaded: '',
    utilisateurs: new Utilisateur,
    data: '',
    archivedDate: ''
  };
  constructor(public fichierService: FichierService,
    private catService: CatServices,
    private adminService: AdminService,
    private authAdmin: AdminAuthService,
    private router: Router,
    private autoServie:AutorisationServices,
    private utilisateurSrvice:UtilisateurService,
    private AutorisationServie:AutorisationServices,
    private route: ActivatedRoute) { }
    
  ngOnInit(): void {
    this.getAllAutorisation()
         this.getAllUtilisateur()
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      if (this.id) {
        this.catService.findByIdCat(this.id).subscribe((data: Cat) => {
          this.cat = data
          this.fichierService.getFileByCatAndEtat(this.id).subscribe((data: Fichier[]) => {
            this.listFile = data
            console.log(data)

          })
        })
      }
    })
    this.getAllFile()
    this.getAllCat()
    if (this.authAdmin.isLoggedIn()) {
      this.adminService.getUserInformation().subscribe((data: Utilisateur) => {
        console.log(data)
        this.utilisateur = data
        this.nomRoles = this.utilisateur.roles[0].nomRoles
      });
    }
    this.getSousCatById()

  }
  public getAllUtilisateur(){
    this.utilisateurSrvice.getAllUtilisateur().subscribe((data:Utilisateur[])=>{
      this.listUtilisateur=data;
    })
  }
  getAllAutorisationByUtilisateur(){
    if(this.authAdmin.isLoggedIn()){
      this.adminService.getUserInformation().subscribe((data:Utilisateur)=>{
        console.log(data)
       this.utilisateur=data
       this.nomRoles=this.utilisateur.roles[0].nomRoles
    
    this.autoServie.getAutorisationByUtilisateur(this.utilisateur.id).subscribe((data:Autorisation[])=>{
      this.autoByUtilisateur=data
      console.log(data)
    })  })
    }
  }
addAcces(id: number) {
  this.fichierService.getById(id).subscribe((data: Fichier) => {
    console.log(data)
    this.fichier=data
  })}
  getAllAutorisation(){
 
    this.autoServie.getAllAutorisation().subscribe((data:Autorisation[])=>{
      this.listAuto=data
      console.log(data)
    })
  }
  getAllFile() {
    this.fichierService.getFileInactive().subscribe((data: Fichier[]) => {
      this.listFichier = data
      console.log(data)
      this.active = !this.active

    })
  }

  displayForm(): boolean {
    return this.active = !this.active
  }
  goToAllFiles() {
    this.router.navigate(['/files']).then(()=>{
      location.reload()
    })
  }
  removeDossier(id: number) {
    this.catService.deleteCat(id).subscribe((data: Cat) => {
      console.log(data)

      window.alert("Dossier deleted")
      this.router.navigate(['/files']).then(()=>{
        location.reload()
      })
      this.getAllCat()
      this.getAllFile()


    })
  }
  
  getSousCatById() {
   

  }
 
  public getAllCat() {
    this.catService.findAllCat().subscribe((data: Cat[]) => {
      this.folders = data
      this.active = !this.active

    },
      (error: HttpErrorResponse) => {
        if (error.status === 403) {
          console.log(error)
          this.router.navigate(['/login']);


        }
      })
  }

  public getFileById(id: number) {
    this.fichierService.getById(id).subscribe((data: Fichier) => {
      console.log(data)
      this.auto.fichier.id=data.id
    }
    )
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }
 
  bytesToMO(bytes: number): number {
    const mb = bytes / 1048576;
    return parseFloat(mb.toFixed(1));
  }

  archiver(id: number) {
    console.log(id)
    this.fichierService.archiveFile(id).subscribe(data => {
      console.log(data)
      const message = (data as any).body
      window.alert(message)
      this.getAllFile()
      this.getSousCatById()
      this.active = !this.active
    })
  }
  public loginOrNot() {
    return this.authAdmin.isLoggedIn();
  }
  logout() {
    this.router.navigate(['/login'])
    return this.authAdmin.clear()
  }
  view(id: number) {
    console.log(id)
    this.router.navigate(['/files', { id }])
  }
  updatePermission(fileId: number, userId: number){
    this.AutorisationServie.grantUpdatePermission(fileId, userId).subscribe((data: Autorisation) => {
      if (data !== null) {
         
        console.log(data)
       window.alert ("READ ACCESS FOR USER "+data.utilisateurs.username +"and fichier "+data.fichier.name+"is granted ");
       location.reload()
     }     else if(data == null) {
      console.log(data)
      window.alert ("READ ACCESS already exist  ");
      location.reload()
    }
  });
}
  downloadPermission(fileId: number, userId: number){
    this.AutorisationServie.grantDownloadPermission(fileId, userId).subscribe((data: Autorisation) => {
      if (data !== null) {
         
        console.log(data)
       window.alert ("READ ACCESS FOR USER "+data.utilisateurs.username +"and fichier "+data.fichier.name+"is granted ");
       location.reload()
     }     else if(data == null) {
      console.log(data)
      window.alert ("READ ACCESS already exist  ");
      location.reload()
    }
  });
}
archivePermission(fileId: number, userId: number){
  
  this.AutorisationServie.grantArchivePermission(fileId, userId).subscribe((data: Autorisation) => {
    if (data !== null) {
         
      console.log(data)
      window.alert ("ARCHIVE ACCESS FOR USER "+data.utilisateurs.username +"and fichier "+data.fichier.name+"is granted ");
      location.reload()
   }else if(data == null) {
    console.log(data)
    window.alert ("ARCHIVE ACCESS already exist  ");
    location.reload()
  }});
}
deletePermission(fileId: number, userId: number){
  this.AutorisationServie.grantDeletePermission(fileId, userId).subscribe((data: Autorisation) => {
    if (data !== null) {         
      console.log(data)
      window.alert ("DELETE ACCESS FOR USER "+data.utilisateurs.username +"and fichier "+data.fichier.name+"is granted ");
      location.reload()
   }else if(data == null) {
    console.log(data)
    window.alert ("DELETE ACCESS already exist  ");
    location.reload()

   }
  });
}
downloadFile(id: number, fileName: string, contentType: string) {
  this.fichierService.getFileById(id).subscribe((data: Blob) => {
    const blob = new Blob([data], { type: contentType });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = fileName;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
  });
}

}
