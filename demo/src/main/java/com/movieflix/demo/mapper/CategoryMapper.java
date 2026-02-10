package com.movieflix.demo.mapper;

import com.movieflix.demo.controllers.request.CategoryRequest;
import com.movieflix.demo.controllers.response.CategoryResponse;
import com.movieflix.demo.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass // Indica que a classe é um utilitário e não deve ser instanciada
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest){
       return Category
               .builder()
               .name(categoryRequest.name())
               .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
