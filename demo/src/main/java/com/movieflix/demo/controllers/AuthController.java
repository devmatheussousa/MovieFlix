package com.movieflix.demo.controllers;

import com.movieflix.demo.controllers.request.UserRequest;
import com.movieflix.demo.controllers.response.UserResponse;
import com.movieflix.demo.repository.UserRepository;
import com.movieflix.demo.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
@Builder
public class AuthController {

    private final UserService userService;

    public ResponseEntity<UserResponse>register(UserRequest request) {

    }
}
