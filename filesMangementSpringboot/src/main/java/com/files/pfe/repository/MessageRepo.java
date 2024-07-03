package com.files.pfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.files.pfe.model.Message;
import com.files.pfe.model.Utilisateurs;

 
public interface MessageRepo extends JpaRepository<Message, Long>  {
    List<Message> findBySenderAndRecipient(Utilisateurs sender, Utilisateurs recipient);
    List<Message> findByRecipientAndSender(Utilisateurs  recipient, Utilisateurs sender );
 
    List<Message> findByRecipient(Utilisateurs recipient);
}
