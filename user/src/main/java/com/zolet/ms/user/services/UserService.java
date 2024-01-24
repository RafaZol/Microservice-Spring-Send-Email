package com.zolet.ms.user.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zolet.ms.user.models.UserModel;
import com.zolet.ms.user.producer.UserProduce;
import com.zolet.ms.user.repositories.UserRepository;

@Service //bean gerenciado pelo spring
public class UserService { // pode ser utilizado como interface
    
    final UserRepository userRepository; // @Autowired
    final UserProduce userProduce; //@Autowired

    public UserService (UserRepository userRepository, UserProduce userProduce){ // ponto de injeção 
        this.userRepository = userRepository;
        this.userProduce = userProduce;
    }

//    2 -> MicroService User publica mensagem e envia para o broker
    @Transactional // garante o rollback, caso uma operação falhe 
    public UserModel save(UserModel userModel){
        userModel = userRepository.save(userModel); //salva o usuario
        userProduce.publishMessageEmail(userModel); // publica a mensagem
        return  userModel;
    }
}
