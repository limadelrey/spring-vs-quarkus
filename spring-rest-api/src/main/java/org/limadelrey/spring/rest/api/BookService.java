package org.limadelrey.spring.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    public Book readOne(UUID id) {
        return bookRepository.findById(id).get();
    }

    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    public void update(Book book, UUID id) {
        book.setId(id);
        bookRepository.save(book);
    }

    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }

}
