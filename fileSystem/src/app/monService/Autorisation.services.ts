import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Cat } from "../monClass/categorie";
import { Autorisation } from "../monClass/Autorisation";

@Injectable({
    providedIn: 'root'
  })
  export class AutorisationServices {
    private apiUrl = 'http://localhost:8080/api/autorisation'; 
  
    constructor(private http: HttpClient) {}

    public getAllAutorisation():Observable<Autorisation[]>{
        return this.http.get<Autorisation[]>(`${this.apiUrl + "/all"}`);
    }
    getGroupedAutorisations(): Observable<{ [key: string]: Autorisation[] }> {
      return this.http.get<{ [key: string]: Autorisation[] }>(`${this.apiUrl}/grouped`);
    }
    public getAutorisationByUtilisateur(id:number):Observable<Autorisation[]>{
      return this.http.get<Autorisation[]>(`${this.apiUrl + "/byUtilisateur/"+id}`);
  }
  deleteAutorization(id:number){
    return this.http.delete(`${this.apiUrl+"/deleteAutorisation/"+id}`)
  }
    grantDownloadPermission(fileId: number, userId: number): Observable<any> {
      return this.http.post(`${this.apiUrl}/${fileId}/permissions/download/`, { userId: userId });
  }
  grantUpdatePermission(fileId: number, userId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${fileId}/permissions/update/`, { userId: userId });
}
    grantDeletePermission(fileId: number, userId: number): Observable<any> {
      return this.http.post(`${this.apiUrl}/${fileId}/permissions/delete/`,  { userId: userId });
    }
  
    grantArchivePermission(fileId: number, userId: number): Observable<any> {
      return this.http.post(`${this.apiUrl}/${fileId}/permissions/archive/`,  { userId: userId });
    }
    
  }