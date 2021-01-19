package org.limadelrey.quarkus.rest.api;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<Book, UUID> {

    public List<Book> readAll() {
        return listAll();
    }

    public Book readOne(UUID id) {
        return find("id = ?1", id).firstResult();
    }

    public Book insert(Book book) {
        persist(book);
        return book;
    }

    public void update(Book book, UUID id) {
        update("author = ?2, title = ?3, price = ?4 where id = ?1", id, book.getAuthor(), book.getTitle(), book.getPrice());
    }

    public void delete(UUID id) {
        delete("id = ?1", id);
    }

}
