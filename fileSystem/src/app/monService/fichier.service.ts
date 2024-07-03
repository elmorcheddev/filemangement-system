import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Fichier } from "../monClass/fichier";
import { Observable, tap } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class FichierService {
    private apiUrl = 'http://localhost:8080/api/file'; // Adjust base URL
  
    constructor(private http: HttpClient) {}
  
     getAllFiles(): Observable<Fichier[]> {
      return this.http.get<Fichier[]>(this.apiUrl + '/all');
    }
    last10(): Observable<Fichier[]> {
      return this.http.get<Fichier[]>(this.apiUrl + '/top10');
    }
     getFileById(id: number): Observable<Blob> {
      return this.http.get(this.apiUrl + '/byId/' + id, { responseType: 'blob' })
    }
    getById(id: number): Observable<Fichier> {
      return this.http.get<Fichier>(this.apiUrl + '/findById/' + id )
    }
    uploadFile(formData: FormData): Observable<Fichier> {
      return this.http.post<Fichier>(this.apiUrl + '/upload', formData);
    }
    getUploadCountByDate(): Observable<any> {
      return this.http.get<any>(this.apiUrl+"/upload-count-by-date");
    }
    getFileByCatAndEtat(id:number):Observable<Fichier[]>{
      return this.http.get<Fichier[]>(`${this.apiUrl+"/all/"+id}`)
    }
    getFileByCatAndEtatFalse(id:number):Observable<Fichier[]>{
      return this.http.get<Fichier[]>(`${this.apiUrl+"/allArchiver/"+id}`)
    }
    getFileActive():Observable<Fichier[]>{
      return this.http.get<Fichier[]>(`${this.apiUrl+"/allActive"}`)
    }
    getFileInactive():Observable<Fichier[]>{
      return this.http.get<Fichier[]>(`${this.apiUrl+"/allDesactive"}`)
    }
    archiveFile(id:number){
      return this.http.get(`${this.apiUrl+"/archive/"+id}`)
    }
    
  }