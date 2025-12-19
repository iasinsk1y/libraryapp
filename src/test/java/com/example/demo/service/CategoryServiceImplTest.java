package com.example.demo.service;

import com.example.demo.models.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void testGetAll() {
        Category c1 = new Category();
        Category c2 = new Category();
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        assertEquals(2, categoryService.getAll().size());
    }

    @Test
    void testAddCategory() {
        Category c = new Category();
        categoryService.addCategory(c);
        verify(categoryRepository, times(1)).save(c);
    }

    @Test
    void testUpdateCategory() {
        Category existing = new Category();
        existing.setId(1L);
        existing.setName("Old");
        Category update = new Category();
        update.setName("New");
        update.setYear(2025);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));
        categoryService.updateCategory(update, 1L);

        verify(categoryRepository).save(existing);
        assertEquals("New", existing.getName());
        assertEquals(2025, existing.getYear());
    }
}
