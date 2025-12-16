package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authorId", source = "author.id")
    BookDto toDto(Book book);
}
