package com.zolet.ms.email.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration //classe de configuração
public class RabbitMQConfig {
    
    @Value("${broker.queue.email.name}") //para acessar o valor da propriedade
    private String queue;

    @Bean // será criada quando necessaria metodo produtor
    public Queue queue(){
        //inicia a fila passando o nome, e se ela será preservada ou não caso o broker pare
        return new Queue(queue, true);
    }

    @Bean //configuração de conversão
    public Jackson2JsonMessageConverter messageConverter(){//recebe o corpo da mensagem em json e converte para passar para o dto
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
