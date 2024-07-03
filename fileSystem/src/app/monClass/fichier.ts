import { Autorisation } from "./Autorisation";
import { Cat } from "./categorie";
import { Utilisateur } from "./utilisateur";
 
export class Fichier{
  id: number;
  data:string
  name: string;
  contentType: string;
  sizeFile:number
  archivedDate:string
  etat:boolean
  categorie:Cat
  dateUploaded:string
  utilisateurs :Utilisateur
  
}