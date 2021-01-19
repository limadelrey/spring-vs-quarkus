package org.limadelrey.quarkus.reactive.rest.api.controller;

import io.reactivex.Single;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.limadelrey.quarkus.reactive.rest.api.model.BookRequest;
import org.limadelrey.quarkus.reactive.rest.api.model.BookResponse;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Tag(name = "Books API", description = "Endpoints regarding book interaction")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("/api/v1/books")
public interface BookRouter {

    @Operation(summary = "Read all books")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    name = "success",
                    description = "Success",
                    content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookResponse.class, type = SchemaType.ARRAY))
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    @GET
    Single<Response> readAll();

    @Operation(summary = "Read a book given its ID")
    @Parameter(name = "id", description = "Book identifier", required = true, example = "a1f13541-57b5-4d35-bb67-608f5cc065ce")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    name = "success", description = "Success",
                    content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookResponse.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "bad request",
                    description = "Bad Request"
            ),
            @APIResponse(
                    responseCode = "404",
                    name = "not found",
                    description = "Not Found"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    @GET
    @Path("{id}")
    Single<Response> readOne(@PathParam("id") UUID id);

    @Operation(summary = "Create a book")
    @Parameter(name = "id", description = "Book identifier", required = true, example = "a1f13541-57b5-4d35-bb67-608f5cc065ce")
    @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookRequest.class)))
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    name = "success",
                    description = "Success",
                    content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookResponse.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "bad request",
                    description = "Bad Request"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    @POST
    Single<Response> create(@Valid BookRequest request);

    @Operation(summary = "Update a book given its ID")
    @Parameter(name = "id", description = "Book identifier", required = true, example = "a1f13541-57b5-4d35-bb67-608f5cc065ce")
    @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookRequest.class)))
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    name = "success",
                    description = "Success",
                    content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = BookResponse.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "bad request",
                    description = "Bad Request"
            ),
            @APIResponse(
                    responseCode = "404",
                    name = "not found",
                    description = "Not Found"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    @PUT
    @Path("{id}")
    Single<Response> update(@Valid BookRequest request, @PathParam("id") UUID id);

    @Operation(summary = "Delete a book given its ID")
    @Parameter(name = "id", description = "Book identifier", required = true, example = "a1f13541-57b5-4d35-bb67-608f5cc065ce")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    name = "success",
                    description = "Success"
            ),
            @APIResponse(
                    responseCode = "400",
                    name = "bad request",
                    description = "Bad Request"
            ),
            @APIResponse(
                    responseCode = "404",
                    name = "not found",
                    description = "Not Found"
            ),
            @APIResponse(
                    responseCode = "500",
                    name = "timeout",
                    description = "Internal Server Error"
            )
    })
    @DELETE
    @Path("{id}")
    Single<Response> delete(@PathParam("id") UUID id);

}
