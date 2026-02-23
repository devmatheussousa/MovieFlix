package com.movieflix.demo.mapper;

import com.movieflix.demo.controllers.request.UserRequest;
import com.movieflix.demo.controllers.response.UserResponse;
import com.movieflix.demo.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass //E uma classe utilitaria, ou seja, nao pode ser instanciada e todos os seus metodos sao estaticos
public class UserMapper {

    //Metodo para converter um UserRequest em um User
    public static User toUser(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
