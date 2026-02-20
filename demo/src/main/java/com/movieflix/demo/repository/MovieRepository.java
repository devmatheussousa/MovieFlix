package com.movieflix.demo.repository;

import com.movieflix.demo.entities.Category;
import com.movieflix.demo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //vai montar a query automaticamente, buscando os filmes que tem a categoria com o id fornecido
    List<Movie> findMovieByCategories(List<Category> categories);
}
