package com.movieflix.demo.controllers;

import com.movieflix.demo.controllers.request.MovieRequest;
import com.movieflix.demo.controllers.request.StreamingRequest;
import com.movieflix.demo.controllers.response.MovieResponse;
import com.movieflix.demo.controllers.response.StreamingResponse;
import com.movieflix.demo.entities.Streaming;
import com.movieflix.demo.mapper.MovieMapper;
import com.movieflix.demo.mapper.StreamingMapper;
import com.movieflix.demo.service.MovieService;
import com.movieflix.demo.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streamings")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;
    private final MovieService movieService;


    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        List<StreamingResponse> streaming = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(streaming);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = streamingService.saveStreaming(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toCategoryResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toCategoryResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreamingById(@PathVariable Long id) {
        streamingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id,
            @RequestBody MovieRequest request){

        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

}
