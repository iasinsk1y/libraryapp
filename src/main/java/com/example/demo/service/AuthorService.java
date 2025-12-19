package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import java.util.List;

public interface AuthorService {
    AuthorDto add(AuthorDto dto);
    AuthorDto getById(Long id);
    List<AuthorDto> getAll();
    AuthorDto update(Long id, AuthorDto dto);
    void delete(Long id);
}
