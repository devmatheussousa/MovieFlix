package com.movieflix.demo.controllers;

import com.movieflix.demo.controllers.request.MovieRequest;
import com.movieflix.demo.controllers.response.MovieResponse;
import com.movieflix.demo.entities.Movie;
import com.movieflix.demo.mapper.MovieMapper;
import com.movieflix.demo.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request){
       Movie savedMovie = movieService.save(MovieMapper.toMovie(request));
       return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

     @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
     }
}
