package com.example.libraryapp.controller;

import com.example.libraryapp.dto.AuthorDto;
import com.example.libraryapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public AuthorDto create(@RequestBody AuthorDto dto) {
        return authorService.create(dto);
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.findAll();
    }
    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id,
                            @RequestBody AuthorDto dto) {
        return authorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

}
