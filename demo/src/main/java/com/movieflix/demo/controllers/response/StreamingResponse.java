package com.movieflix.demo.controllers.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
