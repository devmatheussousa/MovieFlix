package com.movieflix.demo.mapper;

import com.movieflix.demo.controllers.request.MovieRequest;
import com.movieflix.demo.controllers.response.CategoryResponse;
import com.movieflix.demo.controllers.response.MovieResponse;
import com.movieflix.demo.controllers.response.StreamingResponse;
import com.movieflix.demo.entities.Category;
import com.movieflix.demo.entities.Movie;
import com.movieflix.demo.entities.Streaming;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest request){

        // Forma 1: Usando Optional (Muito comum em provas de Java moderno)
        List<Category> categories = Optional.ofNullable(request.categoriesIds())
                .orElse(Collections.emptyList()) // Se for null, vira lista vazia
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = Optional.ofNullable(request.stremingsIds())
                .orElse(Collections.emptyList()) // Se for null, vira lista vazia
                .stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toCategoryResponse)
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
