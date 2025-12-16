package com.example.libraryapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private Integer year;
    private Long authorId;
    private Set<Long> categoryIds;
}
