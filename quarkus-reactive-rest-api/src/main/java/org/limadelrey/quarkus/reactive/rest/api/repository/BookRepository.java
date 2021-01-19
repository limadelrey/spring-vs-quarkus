package org.limadelrey.quarkus.reactive.rest.api.repository;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.vertx.reactivex.pgclient.PgPool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.Tuple;
import org.limadelrey.quarkus.reactive.rest.api.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class BookRepository {

    public static final String SQL_SELECT_ALL = "SELECT id, author, title, price FROM book";
    public static final String SQL_SELECT_BY_ID = "SELECT id, author, title, price FROM book WHERE id = $1";
    public static final String SQL_INSERT_BY_ID = "INSERT INTO book (id, author, title, price) VALUES ($1, $2, $3, $4)";
    public static final String SQL_UPDATE_BY_ID = "UPDATE book SET author = $2, title = $3, price = $4 WHERE id = $1";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM book WHERE id = $1";

    @Inject
    PgPool client;

    public Single<List<Book>> readAll() {
        return client.rxPreparedQuery(SQL_SELECT_ALL)
                .flatMap(result -> {
                    final List<Book> books = new ArrayList<>();

                    final Iterator<Row> iterator = result.iterator();
                    while (iterator.hasNext()) {
                        books.add(Book.of(iterator.next()));
                    }

                    return Single.just(books);
                })
                .onErrorResumeNext(Single::error);
    }

    public Single<Book> readOne(UUID id) {
        return client.rxPreparedQuery(SQL_SELECT_BY_ID, Tuple.of(id))
                .flatMap(result -> {
                    if (result.rowCount() == 0) {
                        return Single.error(new NoSuchElementException("No book with id " + id));
                    }

                    return Single.just(Book.of(result.iterator().next()));
                })
                .onErrorResumeNext(Single::error);
    }

    public Completable insert(Book book) {
        return client.rxPreparedQuery(SQL_INSERT_BY_ID, Tuple.of(book.getId(), book.getAuthor(), book.getTitle(), book.getPrice()))
                .flatMapCompletable(result -> Completable.complete())
                .onErrorResumeNext(Completable::error);
    }

    public Completable update(Book book, UUID id) {
        return client.rxPreparedQuery(SQL_UPDATE_BY_ID, Tuple.of(id, book.getAuthor(), book.getTitle(), book.getPrice()))
                .flatMapCompletable(result -> {
                    if (result.rowCount() == 1) {
                        return Completable.complete();
                    } else {
                        return Completable.error(new NoSuchElementException("No book with id " + id));
                    }
                })
                .onErrorResumeNext(Completable::error);
    }

    public Completable delete(UUID id) {
        return client.rxPreparedQuery(SQL_DELETE_BY_ID, Tuple.of(id))
                .flatMapCompletable(result -> {
                    if (result.rowCount() == 1) {
                        return Completable.complete();
                    } else {
                        return Completable.error(new NoSuchElementException("No book with id " + id));
                    }
                })
                .onErrorResumeNext(Completable::error);
    }

}
