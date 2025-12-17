package com.example.libraryapp.service;

import com.example.libraryapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto dto);
    List<CategoryDto> findAll();
    CategoryDto update(Long id, CategoryDto dto);
    void delete(Long id);
}
