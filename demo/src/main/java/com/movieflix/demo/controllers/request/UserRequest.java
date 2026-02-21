package com.movieflix.demo.controllers.request;

import lombok.Builder;

@Builder
public record UserRequest(String name, String email, String password) {
}
