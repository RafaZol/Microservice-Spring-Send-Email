package com.zolet.ms.email.dto;

import java.util.UUID;

// atributos para o envio do email, id, email do usuario, titulo e conteudo
public record EmailDTO(UUID userId, 
                       String emailTo,
                       String subject,
                       String text) {

    
}
