package com.files.pfe.services;

import java.util.List;

import com.files.pfe.model.Message;
import com.files.pfe.model.Utilisateurs;
 

public interface MessageService {

	Message sendMessage(Message message);
	List<Message> allMsg();
	List<Message> findByRecipientAndSender(Utilisateurs  recipient , Utilisateurs sender);
    List<Message> findBySenderAndRecipient(Utilisateurs  sender, Utilisateurs  recipient );
    List<Message> findByRecipient(Utilisateurs recipient);

	}
