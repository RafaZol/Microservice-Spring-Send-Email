package com.zolet.ms.email.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.zolet.ms.email.enuns.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "tb_email")
public class EmailModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailID;
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "text")
    private String text;
    private LocalDateTime sendDataEmail;
    private StatusEmail statusEmail;
}
