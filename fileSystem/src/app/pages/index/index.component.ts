import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; 
import { Utilisateur } from '../../monClass/utilisateur';
import { Fichier } from '../../monClass/fichier';
import { CatServices } from '../../monService/cat.service';
import { AdminService } from '../../monService/admin.service';
import { UtilisateurService } from '../../monService/utilisateur.service';
import { FichierService } from '../../monService/fichier.service';
import { AdminAuthService } from '../../monService/admin-auth.service';
import * as Highcharts from 'highcharts';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit{
  public chartOptions: Highcharts.Options = {}; 

  utilisateur: Utilisateur;
  nomRoles: string;
  listUtilisateur: Utilisateur[];
  listFichier: Fichier[];
  listFichierInactive: Fichier[];
  userNumber: number;
  fileActive: number;
  fileArchive: number;
  inActiveUser: number;
  activeUser: number;
  uploadData: any;
  chart: Chart<"line", number[], string>;
 

 
 
  constructor(private catService:CatServices, 
              private adminService:AdminService ,
              private utilisateurSrvice:UtilisateurService,
              private fichierService:FichierService,

              private authAdmin:AdminAuthService, private router:Router){}
  ngOnInit(): void {
    if(this.authAdmin.isLoggedIn()){
      this.adminService.getUserInformation().subscribe((data:Utilisateur)=>{
        console.log(data)
       this.utilisateur=data
       this.nomRoles=this.utilisateur.roles[0].nomRoles
      });
    }
    this.getAllUtilisateur()
    this.getAllUtilisateurActive()
    this.getAllUtilisateurInactive()
    this.getAllFile()
    this.getAllFileInactive()
    this.last10()
     this.fichierService.getUploadCountByDate().subscribe(data => {
      console.log(data)
      const labels = data.map((item: { uploadCount: any; }) => item.uploadCount);
      const uploadCounts = data.map((item: { dateUploaded: any; }) => item.dateUploaded);
      const ctx = document.getElementById('doubleSinusoidalChart') as HTMLCanvasElement;
      new Chart(ctx, {
        type: "bar",
        data: {
          labels: uploadCounts,
          datasets: [{
            label: 'Upload Count',
            data:labels ,
            borderColor: 'blue',
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
          }
          }
        }
      });
    });
  
    
  }
 
  
  
  public getAllUtilisateur(){
    this.utilisateurSrvice.getAllUtilisateur().subscribe((data:Utilisateur[])=>{
       this.userNumber=data.length
    })
  }
  public getAllUtilisateurInactive(){
    this.utilisateurSrvice.getAllUtilisateurInactive().subscribe((data:Utilisateur[])=>{
       this.inActiveUser=data.length
    })
  }
  public getAllUtilisateurActive(){
    this.utilisateurSrvice.getAllUtilisateurActive().subscribe((data:Utilisateur[])=>{
       this.activeUser=data.length
    })
  }
  getAllFile() {
    this.fichierService.getFileActive().subscribe((data: Fichier[]) => {
       this.fileActive=data.length
    })
  }
  last10() {
    this.fichierService.last10().subscribe((data: Fichier[]) => {
      this.listFichier = data
     })
  }
  public getAllFileInactive() {
    this.fichierService.getFileInactive().subscribe((data: Fichier[]) => {
      this.listFichierInactive = data
      this.fileArchive=data.length
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
