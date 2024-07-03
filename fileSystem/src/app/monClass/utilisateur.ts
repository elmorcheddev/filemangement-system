import { Autorisation } from "./Autorisation";
import { Roles } from "./Roles";

export class Utilisateur {
        id:number
	    nom:string
	    prenom:string;
	    adresse:string;
	    cin:string ;
	    username:string;
	    password:string;
		etat:boolean
		photo:string
		roles:Roles[]
		autorisations:Autorisation[]
 
}
