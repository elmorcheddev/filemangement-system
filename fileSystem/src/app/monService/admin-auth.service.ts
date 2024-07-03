import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  constructor() { }
  
  public setRoles(roles:[]){
	  localStorage.setItem("roles",JSON.stringify(roles))
  }
    public getRoles():[]{
	return  JSON.parse(localStorage.getItem('roles') as string);
  }
  public setToken(token:string){
	  localStorage.setItem("token",token)
  }
    public getToken(): string{
	return localStorage.getItem('token') as string;
  }
  hasAnyRole(role: string): boolean {
    const userRoles = this.getRoles(); // Assuming getRoles() retrieves user roles
    return userRoles?.some(userRole => userRole === role);
  }
  clear(){
    localStorage.removeItem('token')
    localStorage.removeItem('roles')
	  
  }
  isLoggedIn(){
	  return this.getRoles() && this.getToken();
  }
 
}
