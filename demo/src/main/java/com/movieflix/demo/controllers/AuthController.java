package com.movieflix.demo.controllers;

import com.movieflix.demo.controllers.request.UserRequest;
import com.movieflix.demo.controllers.response.UserResponse;
import com.movieflix.demo.entities.User;
import com.movieflix.demo.mapper.UserMapper;
import com.movieflix.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
@Builder
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse>register(@Valid @RequestBody UserRequest request) {
        User saved = userService.save(UserMapper.toUser(request));
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(saved));
    }
}
