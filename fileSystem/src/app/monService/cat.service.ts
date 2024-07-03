import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Cat } from "../monClass/categorie";

@Injectable({
    providedIn: 'root'
  })
  export class CatServices {
    private apiUrl = 'http://localhost:8080/api/cat'; 
  
    constructor(private http: HttpClient) {}

    public saveNewCat(cat:Cat):Observable<Cat>{
        return this.http.post<Cat>(`${this.apiUrl+"/save"}`,cat)
    }
    public findByIdCat(id:number):Observable<Cat>{
        return this.http.get<Cat>(`${this.apiUrl+"/findById/"+id}`)
    }
    public findByNameCat(nomCat:string):Observable<Cat>{
        return this.http.get<Cat>(`${this.apiUrl+"/findByName/"+nomCat}`)
    }
    public findAllCat():Observable<Cat[]>{
        return this.http.get<Cat[]>(`${this.apiUrl+"/all"}`)
    }
    public deleteCat(id:number){
        return this.http.get<Cat>(`${this.apiUrl+"/deleteByID/"+id}`)
    }
  }