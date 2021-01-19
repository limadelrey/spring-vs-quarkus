package org.limadelrey.quarkus.reactive.rest.api.controller;

import io.reactivex.Single;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.limadelrey.quarkus.reactive.rest.api.model.BookRequest;
import org.limadelrey.quarkus.reactive.rest.api.service.BookService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.UUID;

@ApplicationScoped
public class BookController implements BookRouter {

    @Inject
    BookService bookService;

    @Counted(name = "readAllCount", description = "How many readAll() calls have been performed")
    @Timed(name = "readAllTime", description = "How long readAll() call takes to perform", unit = MetricUnits.MILLISECONDS)
    public Single<Response> readAll() {
        return bookService.readAll()
                .map(result -> Response.ok(result).build());
    }

    @Counted(name = "readOneCount", description = "How many readOne() calls have been performed")
    @Timed(name = "readOneTime", description = "How long readOne() call takes to perform", unit = MetricUnits.MILLISECONDS)
    public Single<Response> readOne(UUID id) {
        return bookService.readOne(id)
                .map(result -> Response.ok(result).build());
    }

    @Counted(name = "createCount", description = "How many create() calls have been performed")
    @Timed(name = "createTime", description = "How long create() call takes to perform", unit = MetricUnits.MILLISECONDS)
    public Single<Response> create(@Valid BookRequest request) {
        return bookService.insert(request)
                .map(result -> Response.status(201).entity(result).build());
    }

    @Counted(name = "updateCount", description = "How many update() calls have been performed")
    @Timed(name = "updateTime", description = "How long update() call takes to perform", unit = MetricUnits.MILLISECONDS)
    public Single<Response> update(@Valid BookRequest request, UUID id) {
        return bookService.update(request, id)
                .andThen(Single.just(Response.noContent().build()));
    }

    @Counted(name = "deleteCount", description = "How many delete() calls have been performed")
    @Timed(name = "deleteTime", description = "How long update() call takes to perform", unit = MetricUnits.MILLISECONDS)
    public Single<Response> delete(UUID id) {
        return bookService.delete(id)
                .andThen(Single.just(Response.noContent().build()));
    }

}
