package com.zolet.ms.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}

	//User MicroServie  --------- Email MicroService -> serviços idependentes que em algum momento se comunicam pela regra de negócio
	//
	//					       		User							        Email
	//		cliente		--> 	MicroService	-->		Broker 	   -->	 MicroService    -->   Email
	//					Message									   Message
	//							.saveUser									.ListemMessage
	//							.publishMessage								.sendEmail
	//																		.saveEmail
	//
	//	cada MicroService usa a própra base de dados para total isolamento
	//	comunicação Assincrona
	//	comunicação via comandos
	//	MicroService user gera uma mensagem que MicroService email vai reeber e realizar
	//	1 -> o cliente envia um POST para cadastrar um novo usuario
	//	2 -> MicroService User publica mensagem e envia para o broker
	//	3 -> MicroService Email lista e consume a mensagem
	//	4 -> MicroService Email envia o email com a mensagem
	//	5 -> MicroService Email salva os dados do email
	//	MicroService user produz a mensagem -> producer
	//	MicroService email consume a mensagem -> comsumer
	//	RabbitMQ funciona com exchange e queues
	// 	O Producer produz a mensagem para o broker, quem recebe essa mensagem é o exchange que analisa o conteudo e faz o roteamento paras as filas(QUEUES)
	//  O Consumer que esta conectado as filas, faz o consumo da mensagem e executa os comandos
	//  Java 17, Maven, Spring (boot, web, jpa, validation, amqp, mail), postgreSQL, RabbitMQ, cloud AMQP, smtp Gmail


}
