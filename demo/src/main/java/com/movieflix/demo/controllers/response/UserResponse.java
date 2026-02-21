package com.movieflix.demo.controllers.response;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email) {
}
