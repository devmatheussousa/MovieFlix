package com.movieflix.demo.service;

import com.movieflix.demo.entities.Category;
import com.movieflix.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final CategoryRepository repository;

    public List<Category> findAll() {
        if (repository.findAll().isEmpty()) {
            return List.of(new Category());
        }
        return repository.findAll();
    }



    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public void deleteByIdCategory(Long id){
        repository.deleteById(id);
    }

}
