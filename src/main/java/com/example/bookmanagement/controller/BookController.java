package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bookmanagement.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> findAllBook(){
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findingBookById(@PathVariable Long id){
        Book book = bookService.finding(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("")
    public ResponseEntity<Book> addingBook(@RequestBody Book req){
        return ResponseEntity.ok(bookService.adding(req));
    }

    @PutMapping("{id}")
    public ResponseEntity<Book>  updatingBook(@PathVariable Long id,@RequestBody Book req){
        return ResponseEntity.ok(bookService.updating(id,req));
    }
    @DeleteMapping("{id}")
    public void deleting(@PathVariable Long id){
        bookService.deleting(id);
    }

}
