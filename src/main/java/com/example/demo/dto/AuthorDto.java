package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    private Long categoryId;
    private List<Long> bookIds = new ArrayList<>();

    private String categoryName;
    private List<String> bookTitles = new ArrayList<>();
}
