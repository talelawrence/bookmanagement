package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    public Book adding(Book req){
        return bookRepository.save(req);
    }

    public Book updating(Long id , Book req){
        Book bookData = bookRepository.findById(id).orElse(null);
        if(bookData != null){
            req.setId(bookData.getId());
            return bookRepository.save(req);
        }else {
            return null;
        }
    }

    public void deleting(Long id){
        bookRepository.deleteById(id);
    }

    public Book finding(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
