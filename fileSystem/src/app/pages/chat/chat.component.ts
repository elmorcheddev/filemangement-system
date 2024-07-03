import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; 
import { Message } from '../../monClass/Message';
import { Utilisateur } from '../../monClass/utilisateur';
import { AdminService } from '../../monService/admin.service';
import { UtilisateurService } from '../../monService/utilisateur.service';
import { AdminAuthService } from '../../monService/admin-auth.service';
import { MessageService } from '../../monService/MessageService';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  utilisateur: any;
  nomRoles: any;
  msglistUser: Message[];
  message: Message={
    idMsg: 0,
    sender: new Utilisateur,
    recipient: new Utilisateur,
    content: '',
    timestamp: ''
  };
  msglistAdmin: Message[];
  listUtilisateur: Utilisateur[];
  sender: Utilisateur={
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    cin: '',
    username: '',
    password: '',
    roles: [],
    autorisations: [],
    etat: false,
    photo: ''
  };
  recepient: Utilisateur={
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    cin: '',
    username: '',
    password: '',
    roles: [],
    autorisations: [],
    etat: false,
    photo: ''
  };
  id: any;
  constructor( 
          
          private adminService: AdminService, 
          private utilisateurService:UtilisateurService,
          private authAdmin: AdminAuthService, 
          private messageService: MessageService,
          private router: Router, private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    if (this.authAdmin.isLoggedIn()) {
      this.adminService.getUserInformation().subscribe((data: any) => {
        console.log(data)
        this.utilisateur = data
        this.nomRoles = this.utilisateur.roles[0].nomRoles
      });
    }
    this.utilisateurService.getAllUtilisateur().subscribe
    (
      (data: Utilisateur[]) => {
      
      this.listUtilisateur = data 
  }) 
      
     this.getMessage(this.id)
   }
  public getMessage(id :number) {
    this.router.navigate(['/chat', { id }])
   
    if (this.authAdmin.isLoggedIn()) {
      this.adminService.getUserInformation().subscribe((data: any) => {
        console.log(data)
        this.utilisateur = data
        this.nomRoles = this.utilisateur.roles[0].nomRoles
        this.messageService.listMessageAdminUser(this.utilisateur.id, id).subscribe(
						(data: Message[]) => {
							this.msglistUser = data
			  	this.utilisateurService.getUtilisateur(this.utilisateur.id).subscribe(
								(data: Utilisateur) => {
									this.sender = data;
									this.message.sender.id = data.id
									this.messageService.listMessageAdminUser(id, this.sender.id).subscribe(
										(data: Message[]) => {
											this.msglistAdmin = data
											this.utilisateurService.getUtilisateur(id).subscribe(
												(data: Utilisateur) => {
													this.recepient = data;
													this.message.recipient.id = data.id
												}
											)
										}
									)
								}
							)  
						}
					)
      });
    }
  		
  }
  loginOrNot() {
    return this.authAdmin.isLoggedIn();
  }
  logout() {
    this.router.navigate(['/login'])
    return this.authAdmin.clear()
  }
  public sendMessage(form: NgForm) {

		this.messageService.sendMessage(this.message).subscribe((data: Message) => {
			this.getMessage(this.message.recipient.id)
			this.message.content = ''
		})


	}
}
