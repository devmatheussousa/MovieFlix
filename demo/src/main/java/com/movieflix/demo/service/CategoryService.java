package com.movieflix.demo.service;

import com.movieflix.demo.entities.Category;
import com.movieflix.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final CategoryRepository repository;

    public List<Category> findAll(){

        if(repository.findAll().isEmpty()){
            throw new RuntimeException("No categories found");
        }

        return repository.findAll();
    }

}
