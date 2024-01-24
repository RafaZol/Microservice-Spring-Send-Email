package com.zolet.ms.user.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zolet.ms.user.dto.UserRecordDTO;
import com.zolet.ms.user.models.UserModel;
import com.zolet.ms.user.services.UserService;

import jakarta.validation.Valid;

@RestController //bean gerenciado pelo spring
public class UserController {

    final UserService userService; // @AutoWired

    public UserController(UserService userService) { //ponto de injeção
        this.userService = userService;
    }
//	1 -> o cliente envia um POST para cadastrar um novo usuario
    @PostMapping("/user") //URI
    public ResponseEntity<UserModel> saveUser(@RequestBody //para receber os dados passados
                                              @Valid //para garantir as validações do record
                                              UserRecordDTO userRecordDTO){ 
        var userModel = new UserModel(); //instância 
        BeanUtils.copyProperties(userRecordDTO, userModel); //trasnfere os dados do redord para o model
        return ResponseEntity.status(HttpStatus.CREATED) //201
        .body(userService.save(userModel)); //retorna o user gerado
    }
}
