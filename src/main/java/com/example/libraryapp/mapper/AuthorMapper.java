package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.AuthorDto;
import com.example.libraryapp.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorDto dto);
    AuthorDto toDto(Author author);
}
