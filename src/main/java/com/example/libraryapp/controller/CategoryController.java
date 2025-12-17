package com.example.libraryapp.controller;

import com.example.libraryapp.dto.CategoryDto;
import com.example.libraryapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto dto) {
        return categoryService.create(dto);
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody CategoryDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
