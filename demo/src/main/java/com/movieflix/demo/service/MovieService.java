package com.movieflix.demo.service;

import com.movieflix.demo.entities.Category;
import com.movieflix.demo.entities.Movie;
import com.movieflix.demo.entities.Streaming;
import com.movieflix.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    //lista um filme
    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }


    public Optional<Movie> update(Long movieId, Movie updatedMovie){

        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if(optMovie.isEmpty()){

            List<Category> categories = this.findCategories(updatedMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updatedMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setRating(updatedMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }

        return  Optional.empty();
    }
}
