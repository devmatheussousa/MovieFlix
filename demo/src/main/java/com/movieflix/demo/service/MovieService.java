package com.movieflix.demo.service;

import com.movieflix.demo.entities.Category;
import com.movieflix.demo.entities.Movie;
import com.movieflix.demo.entities.Streaming;
import com.movieflix.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    private List<Category> findCategories(List<Category> categories){

        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(
                category -> {
                    categoryService.getCategoryById(category.getId()).ifPresent(categoriesFound::add);
                });
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(
                streaming -> {
                    streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add);
                });
        return streamingsFound;
    }
}
