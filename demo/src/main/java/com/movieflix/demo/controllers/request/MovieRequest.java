package com.movieflix.demo.controllers.request;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        String title,
        String description,
        LocalDate releaseDate,
        Double rating,
        List<Long> categoriesIds,
        List<Long> stremingsIds
) {
}
