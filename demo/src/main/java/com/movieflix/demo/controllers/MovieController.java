package com.movieflix.demo.controllers;

import com.movieflix.demo.controllers.request.MovieRequest;
import com.movieflix.demo.controllers.response.MovieResponse;
import com.movieflix.demo.entities.Movie;
import com.movieflix.demo.mapper.MovieMapper;
import com.movieflix.demo.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request){
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

     //lista um filme
        @GetMapping("/{id}")
        public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
            return  movieService.findAll().stream()
                    .filter(movie -> movie.getId().equals(id)) // Filtra a lista de filmes para encontrar o que tem o ID igual ao fornecido
                    .findFirst() // Retorna o primeiro filme encontrado (se houver)
                    .map(MovieMapper::toMovieResponse)// Converte o filme encontrado para MovieResponse usando o MovieMapper
                    .map(ResponseEntity::ok)// Envolve o MovieResponse em um ResponseEntity com status 200 OK
                    .orElse(ResponseEntity.notFound().build()); // Se nenhum filme for encontrado, retorna um ResponseEntity com status 404 Not Found
        }


    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody MovieRequest request){
        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
        return ResponseEntity.ok(movieService.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        movieService.findById(id).ifPresent(movie -> movieService.deleteById(movie.getId()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
