package com.movieflix.demo.controllers.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        @NotEmpty(message = "O título do filme é obrigatório")
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") // Para garantir que a data seja enviada no formato correto (ex: "2024-06-01")
        LocalDate releaseDate,
        Double rating,
        List<Long> categoriesIds,
        List<Long> stremingsIds
) {
}
