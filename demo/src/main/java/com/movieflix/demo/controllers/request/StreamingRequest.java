package com.movieflix.demo.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "Nome de servico de streaming e obrigatorio") String name) {
}
