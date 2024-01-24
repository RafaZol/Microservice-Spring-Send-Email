package com.zolet.ms.user.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data //getter setter
@Entity
@Table(name = "tb_user")
public class UserModel implements Serializable{ //controle de versoes
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//gera id automaticamente
    private UUID id; //UUID para evitar confiltos
    
    private String name;
    
    private String email;


}
