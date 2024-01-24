package com.zolet.ms.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zolet.ms.email.enuns.StatusEmail;
import com.zolet.ms.email.model.EmailModel;
import com.zolet.ms.email.repositories.EmailRepository;

@Service
public class EmailService {
    
    final EmailRepository emailRepository; //@Autowired salvar email na base de dados
    final JavaMailSender javaMailSender;//@Autowired usado para fazer o envio do email

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender){ //ponto de injeçao
        this.emailRepository = emailRepository;
        this.javaMailSender =javaMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom; //email passado no aplication properties

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel){
        try {
        emailModel.setSendDataEmail(LocalDateTime.now()); // data de envio do email
        emailModel.setEmailFrom(emailFrom); 

        SimpleMailMessage message = new SimpleMailMessage(); // criação da mensagem de email
        message.setTo(emailModel.getEmailTo());// destinatário
        message.setSubject(emailModel.getSubject()); // titulo do email
        message.setText(emailModel.getText()); // texto do email
        javaMailSender.send(message); // enviar o emais
        emailModel.setStatusEmail(StatusEmail.SEND); // email enviado
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERRO); // erro na execução
        } finally {
//      5 -> MicroService Email salva os dados do email
            return emailRepository.save(emailModel);
        }
    }
}
