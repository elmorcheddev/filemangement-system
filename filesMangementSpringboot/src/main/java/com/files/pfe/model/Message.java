package com.files.pfe.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMsg;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Utilisateurs sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Utilisateurs recipient;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date timestamp;
}

