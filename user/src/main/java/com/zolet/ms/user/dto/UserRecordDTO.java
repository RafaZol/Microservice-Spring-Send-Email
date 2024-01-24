package com.zolet.ms.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//para utilizar o record os atributos estão presentes como paramêtros 
public record UserRecordDTO(@NotBlank String name, 
                            @NotBlank //validações para não permitir valores brancos ou nulos
                            @Email String email) { // @Email para validar o email informado
                                
    
}
