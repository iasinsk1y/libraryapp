package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.CategoryDto;
import com.example.libraryapp.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto dto);
    CategoryDto toDto(Category category);
}
