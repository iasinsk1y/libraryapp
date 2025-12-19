package com.example.demo.service.impl;

import com.example.demo.dto.AuthorDto;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.models.Author;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             CategoryRepository categoryRepository,
                             BookRepository bookRepository,
                             AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    private AuthorDto toDto(Author a) {
        if (a == null) return null;
        AuthorDto dto = new AuthorDto();
        dto.setId(a.getId());
        dto.setFirstName(a.getFirstName());
        dto.setLastName(a.getLastName());
        dto.setAge(a.getAge());
        if (a.getCategory() != null) {
            dto.setCategoryId(a.getCategory().getId());
            dto.setCategoryName(a.getCategory().getName());
        }
        List<Long> bookIds = new ArrayList<>();
        List<String> bookTitles = new ArrayList<>();
        if (a.getBooks() != null) {
            for (Book b : a.getBooks()) {
                if (b == null) continue;
                bookIds.add(b.getId());
                bookTitles.add(b.getTitle());
            }
        }
        dto.setBookIds(bookIds);
        dto.setBookTitles(bookTitles);
        return dto;
    }

    @Override
    public AuthorDto add(AuthorDto dto) {
        Author a = new Author();
        a.setFirstName(dto.getFirstName());
        a.setLastName(dto.getLastName());
        a.setAge(dto.getAge() != null ? dto.getAge() : 0);

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            a.setCategory(category);
        }

        Set<Book> bookSet = new HashSet<>();
        if (dto.getBookIds() != null) {
            for (Long id : dto.getBookIds()) {
                bookRepository.findById(id).ifPresent(bookSet::add);
            }
        }
        a.setBooks(bookSet);

        Author saved = authorRepository.save(a);
        return toDto(saved);
    }

    @Override
    public AuthorDto getById(Long id) {
        return authorRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> list = new ArrayList<>();
        for (Author a : authors) {
            list.add(toDto(a));
        }
        return list;
    }

    @Override
    public AuthorDto update(Long id, AuthorDto dto) {
        Author existing = authorRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setAge(dto.getAge() != null ? dto.getAge() : existing.getAge());

        if (dto.getCategoryId() != null) {
            categoryRepository.findById(dto.getCategoryId()).ifPresent(existing::setCategory);
        } else {
            existing.setCategory(null);
        }

        Set<Book> bookSet = new HashSet<>();
        if (dto.getBookIds() != null) {
            for (Long bId : dto.getBookIds()) {
                bookRepository.findById(bId).ifPresent(bookSet::add);
            }
        }
        existing.setBooks(bookSet);

        Author updated = authorRepository.save(existing);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
