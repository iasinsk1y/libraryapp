package com.example.libraryapp.service.impl;

import com.example.libraryapp.dto.AuthorDto;
import com.example.libraryapp.mapper.AuthorMapper;
import com.example.libraryapp.model.Author;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto create(AuthorDto dto) {
        Author author = authorMapper.toEntity(dto);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    @Override
    public AuthorDto update(Long id, AuthorDto dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(dto.getName());

        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
