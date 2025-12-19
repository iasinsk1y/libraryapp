package com.example.demo.service;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    private BookRepository bookRepository;
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void testGetAll() {
        Book b1 = new Book();
        b1.setId(1L);
        Book b2 = new Book();
        b2.setId(2L);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(b1, b2));
        assertEquals(2, bookService.getAll().size());
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        bookService.addBook(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook() {
        Book existing = new Book();
        existing.setId(1L);
        existing.setTitle("Old");
        existing.setPages(300);
        Book update = new Book();
        update.setTitle("New");
        update.setPages(500);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));

        bookService.updateBook(update, 1L);

        verify(bookRepository).save(existing);
        assertEquals("New", existing.getTitle());
        assertEquals(500, existing.getPages());
    }
}
