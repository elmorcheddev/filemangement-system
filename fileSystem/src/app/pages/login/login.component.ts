import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'; 
import { AdminService } from '../../monService/admin.service';
import { AdminAuthService } from '../../monService/admin-auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  message: string;
  utilisateur: any;
  constructor(private router: Router, private adminService: AdminService, private authAdmin: AdminAuthService) {}
  ngOnInit(): void {
   }
   login(form: NgForm) {
    console.log(form.value)
    this.adminService.loginAdmin(form.value).subscribe(
      (data: any) => {
        console.log(data)
        this.authAdmin.setRoles(data.utilisateur.roles);
        this.authAdmin.setToken(data.token);
        const roles = data.utilisateur.roles[0].nomRoles;
        if(data.utilisateur.etat == true){
        window.alert("Bien venu " + data.utilisateur.username);
        this.router.navigate(['/home']).then(() => {
          window.location.reload();
        });
      }else{
        window.alert("Votre compte a ete desactive ");
        this.authAdmin.clear()
        this.router.navigate(['/login']).then(() => {
          window.location.reload();
        });
      }},
      (error: HttpErrorResponse) => {
        if (error.status === 403) {
          this.message = "Vérifier votre nom d'utilisateur ou votre mot de passe.";
          this.router.navigate(['/login']);
        }
      }
    );
  }
  
}
