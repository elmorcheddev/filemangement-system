package com.files.pfe.controller;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

import com.files.pfe.model.Message;
import com.files.pfe.model.Utilisateurs;
 import com.files.pfe.services.MessageService;
import com.files.pfe.services.UtilisateurService;

 
@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
 
	@Autowired
	private MessageService messageService;
	@Autowired
	private UtilisateurService utilisateurService; 
@PostMapping(value = "/send")
public Message sendMessage(@RequestBody Message message) {
	return messageService.sendMessage(message);
}
@GetMapping(value = "/list/{idadmin}/{iduser}")
public List<Message> AllMessage(@PathVariable("iduser") Long iduser
					,@PathVariable("idadmin") Long idadmin) 
{
	Utilisateurs recepter=utilisateurService.findBYiDUtilisateurs(iduser);
	Utilisateurs sender=utilisateurService.findBYiDUtilisateurs(idadmin);
	return messageService.findByRecipientAndSender(recepter, sender);	


}
@GetMapping(value = "/listuser/{iduser}/{idadmin}")
public List<Message> AllUserMessage(@PathVariable("iduser") Long iduser
					,@PathVariable("idadmin") Long idadmin) 
{
	Utilisateurs sender =utilisateurService.findBYiDUtilisateurs(iduser);
	Utilisateurs recepter =utilisateurService.findBYiDUtilisateurs(idadmin);
	return messageService.findBySenderAndRecipient(sender, recepter);	


}
@GetMapping(value = "myMessage/{id}")
public List<Message> findByRecipient(@PathVariable Long id) {
		Utilisateurs recipient = utilisateurService.findBYiDUtilisateurs(id);
	return messageService.findByRecipient(recipient);
}
}