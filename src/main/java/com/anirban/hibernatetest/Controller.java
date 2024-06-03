package com.anirban.hibernatetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/book")
public class Controller {

    @Autowired
    BookRepo booksDAO;

    @PostMapping("/admin/save")
    public Book saveBook(@RequestBody Book book) {
        return booksDAO.save(book);
    }

    @PostMapping("/admin/saveAll")
    public List<Book> saveAllBook(@RequestBody List<Book> books) {
        return booksDAO.saveAll(books);
    }

    @GetMapping("getById")
    public Optional<Book> getBookById(@RequestParam int bookId){
        return booksDAO.findById(bookId);
    }

    @GetMapping("/user/getAll")
    public List<Book> getAllBook(){
        return booksDAO.findAll();
    }
}
