import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Message } from '../monClass/Message';
 
@Injectable({
  providedIn: 'root'
})
export class MessageService {
PATH_APP="http://localhost:8080/api/message/";
constructor(private httpClient:HttpClient){}
public sendMessage(message:Message):Observable<Message>{
	return this.httpClient.post<Message>(`${this.PATH_APP + "send"}`,message)
}
public listMessageAdminUser(iduser:number,idadmin:number):Observable<Message[]>{
	return this.httpClient.get<Message[]>(`${this.PATH_APP +"list/"+idadmin+"/"+iduser}`)
}


}