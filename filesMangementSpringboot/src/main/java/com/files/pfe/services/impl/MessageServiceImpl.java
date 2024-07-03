package com.files.pfe.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.files.pfe.model.Message;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.repository.MessageRepo;
import com.files.pfe.services.MessageService;
import com.files.pfe.services.UtilisateurService;
 
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private MessageRepo messageRepo;
	@Override
	public Message sendMessage(Message message) {
		Utilisateurs sendToUtilisateur = utilisateurService.findBYiDUtilisateurs(message.getSender().getId());
		Utilisateurs receptUtilisateur = utilisateurService.findBYiDUtilisateurs(message.getRecipient().getId());
		message.setSender(sendToUtilisateur);
		message.setRecipient(receptUtilisateur);
		message.setContent(message.getContent());
		message.setTimestamp(new Date());
		return messageRepo.save(message);
	}
	
	



	@Override
	public List<Message> allMsg() {
		// TODO Auto-generated method stub
		return messageRepo.findAll();
	}





	@Override
public	List<Message> findByRecipientAndSender(Utilisateurs  sender , Utilisateurs recipient )
	{
		
	 return messageRepo.findBySenderAndRecipient(sender, recipient);
		
	}





	@Override
	public List<Message> findBySenderAndRecipient(Utilisateurs sender, Utilisateurs recipient) {
		// TODO Auto-generated method stub
		return messageRepo.findByRecipientAndSender(recipient, sender);
	}





	@Override
	public List<Message> findByRecipient(Utilisateurs recipient) {
		// TODO Auto-generated method stub
		return messageRepo.findByRecipient(recipient);
	}





	 



}
