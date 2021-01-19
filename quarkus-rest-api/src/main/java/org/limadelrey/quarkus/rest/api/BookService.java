package org.limadelrey.quarkus.rest.api;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public List<Book> readAll() {
        return bookRepository.readAll();
    }

    public Book readOne(UUID id) {
        return bookRepository.readOne(id);
    }

    @Transactional
    public Book insert(Book book) {
        book.setId(UUID.randomUUID());

        return bookRepository.insert(book);
    }

    @Transactional
    public void update(Book book, UUID id) {
        bookRepository.update(book, id);
    }

    @Transactional
    public void delete(UUID id) {
        bookRepository.delete(id);
    }

}
