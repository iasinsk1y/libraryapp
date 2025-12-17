package com.example.libraryapp.service;

import com.example.libraryapp.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto create(BookDto dto);
    List<BookDto> findAll();
    BookDto update(Long id, BookDto dto);
    void delete(Long id);
}
