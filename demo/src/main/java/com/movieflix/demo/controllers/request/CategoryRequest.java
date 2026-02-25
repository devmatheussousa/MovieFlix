package com.movieflix.demo.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "Nome da categoria e obrigatorio") String name) { //NotEmpty garante que o campo name não seja nulo ou vazio, garantindo que a categoria tenha um nome válido ao ser criada ou atualizada.
}
