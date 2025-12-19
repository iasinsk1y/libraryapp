package com.example.demo.service.impl;

import com.example.demo.models.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category, Long id) {
        Category update = categoryRepository.findById(id).orElse(null);
        update.setName(category.getName());
        update.setYear(category.getYear());
        categoryRepository.save(update);
    }
}
