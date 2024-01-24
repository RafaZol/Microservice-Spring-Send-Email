package com.zolet.ms.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zolet.ms.user.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID>{
    //extente o JpaRepository para poder usar todas funcionalidades do spring, passando a entidade,e o tipo de identificador
}
