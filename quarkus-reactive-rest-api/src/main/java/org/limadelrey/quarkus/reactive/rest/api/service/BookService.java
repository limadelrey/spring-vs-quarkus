package org.limadelrey.quarkus.reactive.rest.api.service;

import io.reactivex.Completable;
import io.reactivex.Single;
import org.limadelrey.quarkus.reactive.rest.api.model.BookRequest;
import org.limadelrey.quarkus.reactive.rest.api.model.BookResponse;
import org.limadelrey.quarkus.reactive.rest.api.repository.BookRepository;
import org.limadelrey.quarkus.reactive.rest.api.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Single<List<BookResponse>> readAll() {
        return bookRepository.readAll()
                .map(books -> books.stream()
                        .map(BookResponse::of)
                        .collect(Collectors.toList())
                );
    }

    public Single<BookResponse> readOne(UUID id) {
        return bookRepository.readOne(id)
                .map(BookResponse::of);
    }

    public Single<BookResponse> insert(BookRequest request) {
        final Book book = request.toBook(UUID.randomUUID());

        return bookRepository.insert(book)
                .andThen(Single.just(BookResponse.of(book)));
    }

    public Completable update(BookRequest request, UUID id) {
        final Book book = request.toBook();

        return bookRepository.update(book, id);
    }

    public Completable delete(UUID id) {
        return bookRepository.delete(id);
    }

}
