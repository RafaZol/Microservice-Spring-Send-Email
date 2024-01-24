package com.zolet.ms.email.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.zolet.ms.email.dto.EmailDTO;
import com.zolet.ms.email.model.EmailModel;
import com.zolet.ms.email.services.EmailService;


@Component //bean genérico gerenciado pelo spring
public class EmailConsumer {

    final EmailService emailService; //@Autowired

    public EmailConsumer(EmailService emailService){ //ponto de injeção
        this.emailService = emailService;
    }
//    3 -> MicroService Email lista e consume a mensagem
    @RabbitListener(queues = "${broker.queue.email.name}") //o nome da fila que o comsumer irá acessar no broker
    public void listenEmailQueue(@Payload EmailDTO emailDTO){ //payload, recebe o corpo da mensagem
        var emailModel = new EmailModel(); //intância
        BeanUtils.copyProperties(emailDTO, emailModel); // tranfere os dados do record prara o model
//    4 -> MicroService Email envia o email com a mensagem
        emailService.sendEmail(emailModel); //envia o email com a mensagem
    }
}
