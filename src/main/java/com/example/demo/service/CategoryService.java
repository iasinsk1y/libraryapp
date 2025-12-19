package com.example.demo.service;

import com.example.demo.models.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    void addCategory(Category category);
    void updateCategory(Category category, Long id);
}
