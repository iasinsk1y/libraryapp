package com.example.demo.service.impl;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    @Override
    public void addBook(Book book){
        bookRepository.save(book);
    }
    @Override
    public void updateBook(Book book, Long id){
        Book upd = bookRepository.findById(id).orElse(null);
        upd.setPages(book.getPages());
        upd.setTitle(book.getTitle());
        bookRepository.save(upd);
    }
}
