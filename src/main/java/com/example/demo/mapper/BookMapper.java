package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.models.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);
    Book toEntity(BookDto dto);

}
