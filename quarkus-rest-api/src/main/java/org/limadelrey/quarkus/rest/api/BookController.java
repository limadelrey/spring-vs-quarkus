package org.limadelrey.quarkus.rest.api;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/api/v1/books")
public class BookController {

    @Inject
    BookService bookService;

    @GET
    public Response readAll() {
        return Response.ok(bookService.readAll()).build();
    }

    @GET
    @Path("{id}")
    public Response readOne(@PathParam("id") UUID id) {
        return Response.ok(bookService.readOne(id)).build();
    }

    @POST
    public Response create(@Valid Book book) {
        return Response.status(201).entity(bookService.insert(book)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(Book book, @PathParam("id") UUID id) {
        bookService.update(book, id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") UUID id) {
        bookService.delete(id);
        return Response.noContent().build();
    }

}
