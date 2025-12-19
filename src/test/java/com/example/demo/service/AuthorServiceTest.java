package com.example.demo.service.impl;

import com.example.demo.dto.AuthorDto;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.models.Author;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author makeAuthor(Long id, String firstName, String lastName, int age, Category category, Set<Book> books) {
        Author a = new Author();
        a.setId(id);
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setAge(age);
        a.setCategory(category);
        a.setBooks(books != null ? books : new HashSet<>());
        return a;
    }

    private AuthorDto makeDto(Long id, String firstName, String lastName, Integer age, Long categoryId, List<Long> bookIds) {
        AuthorDto dto = new AuthorDto();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setAge(age);
        dto.setCategoryId(categoryId);
        dto.setBookIds(bookIds != null ? bookIds : new ArrayList<>());
        return dto;
    }

    @Test
    void add_ShouldSaveAuthorAndReturnDto() {
        AuthorDto input = makeDto(null, "Ivan", "Petrov", 21, 1L, Arrays.asList(1L, 2L));

        Category category = new Category(1L, 2024, "CS-101");
        Book book1 = new Book(1L, 300, "Java");
        Book book2 = new Book(2L, 400, "DB");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));

        Set<Book> savedBooks = new HashSet<>(Arrays.asList(book1, book2));
        Author saved = makeAuthor(10L, "Ivan", "Petrov", 21, category, savedBooks);
        when(authorRepository.save(any(Author.class))).thenReturn(saved);
        AuthorDto result = authorService.add(input);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getFirstName()).isEqualTo("Ivan");
        assertThat(result.getCategoryId()).isEqualTo(1L);
        assertThat(result.getCategoryName()).isEqualTo("CS-101");
        assertThat(result.getBookIds()).containsExactlyInAnyOrder(1L, 2L);
        assertThat(result.getBookTitles()).containsExactlyInAnyOrder("Java", "DB");

        verify(categoryRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(2L);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void getById_WhenExists_ShouldReturnDto() {
        Category category = new Category(2L, 2025, "CS-102");
        Book b = new Book(5L, 300, "Algorithms");
        Author a = makeAuthor(5L, "Anna", "Smith", 20, category, new HashSet<>(Collections.singletonList(b)));

        when(authorRepository.findById(5L)).thenReturn(Optional.of(a));

        AuthorDto dto = authorService.getById(5L);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getFirstName()).isEqualTo("Anna");
        assertThat(dto.getCategoryName()).isEqualTo("CS-102");
        assertThat(dto.getBookTitles()).contains("Algorithms");

        verify(authorRepository, times(1)).findById(5L);
    }

    @Test
    void getById_WhenNotFound_ShouldReturnNull() {
        when(authorRepository.findById(999L)).thenReturn(Optional.empty());

        AuthorDto dto = authorService.getById(999L);

        assertThat(dto).isNull();
        verify(authorRepository, times(1)).findById(999L);
    }

    @Test
    void getAll_ShouldReturnAllDtos() {
        Author a1 = makeAuthor(1L, "A", "B", 20, null, null);
        Author a2 = makeAuthor(2L, "C", "D", 22, null, null);

        when(authorRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<AuthorDto> list = authorService.getAll();

        assertThat(list).isNotNull();
        assertThat(list).hasSize(2);
        assertThat(list).extracting(AuthorDto::getId).containsExactlyInAnyOrder(1L, 2L);

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void update_WhenAuthorMissing_ShouldReturnNull() {
        AuthorDto updateDto = makeDto(null, "X", "Y", 30, null, Collections.emptyList());
        when(authorRepository.findById(999L)).thenReturn(Optional.empty());

        AuthorDto res = authorService.update(999L, updateDto);

        assertThat(res).isNull();
        verify(authorRepository, times(1)).findById(999L);
    }

    @Test
    void update_WhenExists_ShouldSaveAndReturnDto() {
        Category oldCategory = new Category(1L, 2024, "CS-101");
        Author existing = makeAuthor(7L, "Old", "Name", 19, oldCategory, new HashSet<>());

        when(authorRepository.findById(7L)).thenReturn(Optional.of(existing));

        AuthorDto updateDto = makeDto(null, "New", "Surname", 21, null, Arrays.asList());

        Author updated = makeAuthor(7L, "New", "Surname", 21, null, new HashSet<>());
        when(authorRepository.save(any(Author.class))).thenReturn(updated);

        AuthorDto res = authorService.update(7L, updateDto);

        assertThat(res).isNotNull();
        assertThat(res.getId()).isEqualTo(7L);
        assertThat(res.getFirstName()).isEqualTo("New");
        assertThat(res.getCategoryId()).isNull();

        verify(authorRepository, times(1)).findById(7L);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        doNothing().when(authorRepository).deleteById(3L);

        authorService.delete(3L);

        verify(authorRepository, times(1)).deleteById(3L);
    }
}
