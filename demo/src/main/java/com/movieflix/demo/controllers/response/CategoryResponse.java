package com.movieflix.demo.controllers.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
