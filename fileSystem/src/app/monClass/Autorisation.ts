import { Fichier } from "./fichier";
 import { Utilisateur } from "./utilisateur";

export class Autorisation {
    id: number;
    permission:string
    fichier: Fichier;
    utilisateurs: Utilisateur;
}