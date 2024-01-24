package com.zolet.ms.user.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class EmailDTO { // para fazer a comunicação entre os micoservices
    private UUID userId; // id
    private String emailTo; // email do usuario
    private String subject; // titulo
    private String text; //conteudo
}
