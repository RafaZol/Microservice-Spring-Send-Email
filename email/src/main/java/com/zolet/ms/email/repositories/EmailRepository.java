package com.zolet.ms.email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zolet.ms.email.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel,UUID>{
    
}
