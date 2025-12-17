package com.example.libraryapp.service.impl;

import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.model.Author;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.Category;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import com.example.libraryapp.repository.CategoryRepository;
import com.example.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto create(BookDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Set<Category> categories = new HashSet<>(
                categoryRepository.findAllById(dto.getCategoryIds())
        );

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());
        book.setAuthor(author);
        book.setCategories(categories);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Set<Category> categories = new HashSet<>(
                categoryRepository.findAllById(dto.getCategoryIds())
        );

        book.setTitle(dto.getTitle());
        book.setYear(dto.getYear());
        book.setAuthor(author);
        book.setCategories(categories);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}


