import { Utilisateur } from "./utilisateur";

 
export interface Message{
	 idMsg:number;

  sender:Utilisateur;

 recipient:Utilisateur;

 content:string;

timestamp:string;
}