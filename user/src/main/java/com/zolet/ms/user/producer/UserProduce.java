package com.zolet.ms.user.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zolet.ms.user.dto.EmailDTO;
import com.zolet.ms.user.models.UserModel;

@Component //classe genérica gerenciada pelo spring
public class UserProduce { //classe para produzir e publicar a mensagem

    final RabbitTemplate rabbitTemplate; //@Autowired

    public UserProduce(RabbitTemplate rabbitTemplate) { //ponto de injeção
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}") //nome da fila usado como routingkey
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) { //publica a mensagem na fila publishMessageInQueue
        var emailDto = new EmailDTO(); //instância
        emailDto.setUserId(userModel.getId()); //id a ser passado
        emailDto.setEmailTo(userModel.getEmail()); // email do usuario
        emailDto.setSubject("Cadastro realizado com sucesso!"); //titulo
        emailDto.setText(userModel.getName() + ", Seu cadastro foi realizado com sucesso\nPara mais detalhes acesse sua conta!");//conteudo

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
        // como a exchange é default, é enviado string vazia(troca padrão), routingkey utilizada para o redirecionamento, corpo da mensagem
    }

}