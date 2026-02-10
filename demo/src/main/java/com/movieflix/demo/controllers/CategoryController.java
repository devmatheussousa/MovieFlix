package com.movieflix.demo.controllers;

import com.movieflix.demo.controllers.request.CategoryRequest;
import com.movieflix.demo.controllers.response.CategoryResponse;
import com.movieflix.demo.entities.Category;
import com.movieflix.demo.mapper.CategoryMapper;
import com.movieflix.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController{

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
         List<CategoryResponse> categories = categoryService.findAll()
                 .stream()
                 .map(CategoryMapper::toCategoryResponse)
                 .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> savaCategory(@RequestBody CategoryRequest request){
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteByIdCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


