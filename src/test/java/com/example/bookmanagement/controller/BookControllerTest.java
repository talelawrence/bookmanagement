package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class BookControllerTest {
    @Mock
    BookService bookService;
    @InjectMocks
    BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllBook() {
        when(bookService.findAll()).thenReturn(List.of(MockBookData()));

        ResponseEntity<List<Book>> result = bookController.findAllBook();
        Assertions.assertEquals(List.of(MockBookData()).toString(), result.getBody().toString());
    }

    @Test
    void testFindingBookById() {
        when(bookService.finding(1L)).thenReturn(MockBookData());

        ResponseEntity<Book> result = bookController.findingBookById(1L);
        Assertions.assertEquals(MockBookData().toString(), result.getBody().toString());
    }

    @Test
    void testFindingBookByIdWithNull() {
        when(bookService.finding(2L)).thenReturn(null);

        ResponseEntity<Book> result = bookController.findingBookById(2L);
        Assertions.assertEquals(null, result.getBody());
    }

    @Test
    void testAddingBookSuccess() {
        when(bookService.adding(any())).thenReturn(MockBookData());

        ResponseEntity<Book> result = bookController.addingBook(MockBookData());

        Assertions.assertEquals(MockBookData().toString(), result.getBody().toString());
    }

    @Test
    void testUpdatingBookSuccess() {
        Book mockBook = MockBookData();
        mockBook.setIsbn("978-0-441-17271-0");
        mockBook.setTitle("DUNE PART 2");
        mockBook.setPublishedDate(LocalDate.of(2025, Month.JANUARY, 01));
        when(bookService.updating(anyLong(), any())).thenReturn(MockBookDataUpdate());

        ResponseEntity<Book> result = bookController.updatingBook(1L,mockBook);
        Assertions.assertEquals(MockBookDataUpdate().getIsbn(), result.getBody().getIsbn());
        Assertions.assertEquals(MockBookDataUpdate().getTitle(), result.getBody().getTitle());
    }

    @Test
    void testDeletingReturnSuccess() {
        Long bookId = 1L;
        doNothing().when(bookService).deleting(bookId);
        bookController.deleting(bookId);
        verify(bookService, times(1)).deleting(bookId);
    }

    private Book MockBookData(){
        return new Book(Long.valueOf(1), "DUNE PART 1", "frank herbert", "978-0-441-17271-9", LocalDate.of(2024, Month.JULY, 31));
    }

    private Book MockBookDataUpdate(){
        return new Book(Long.valueOf(1), "DUNE PART 2", "frank herbert", "978-0-441-17271-0", LocalDate.of(2025, Month.JANUARY, 01));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme