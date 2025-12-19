package com.example.demo.service;

import com.example.demo.models.Book;
import java.util.List;

public interface BookService {
    List<Book> getAll();
    void addBook(Book book);
    void updateBook(Book book, Long id);
}
