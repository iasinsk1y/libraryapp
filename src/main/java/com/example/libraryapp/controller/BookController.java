package com.example.libraryapp.controller;

import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDto create(@RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id,
                          @RequestBody BookDto dto) {
        return bookService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
