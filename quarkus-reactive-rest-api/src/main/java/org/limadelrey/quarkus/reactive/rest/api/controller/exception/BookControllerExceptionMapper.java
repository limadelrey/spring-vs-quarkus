package org.limadelrey.quarkus.reactive.rest.api.controller.exception;

import com.fasterxml.jackson.core.JsonParseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.NoSuchElementException;

@Provider
public class BookControllerExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        final Response.Status code;

        if (exception instanceof JsonParseException) {
            code = Response.Status.BAD_REQUEST;
        } else if (exception instanceof NoSuchElementException) {
            code = Response.Status.NOT_FOUND;
        } else {
            code = Response.Status.INTERNAL_SERVER_ERROR;
        }

        return Response
                .status(code)
                .entity(Map.of("error", exception.getMessage()))
                .build();
    }

}
