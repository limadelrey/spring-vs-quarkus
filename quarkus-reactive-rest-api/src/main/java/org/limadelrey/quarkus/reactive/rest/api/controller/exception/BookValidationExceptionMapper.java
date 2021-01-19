package org.limadelrey.quarkus.reactive.rest.api.controller.exception;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class BookValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        final String exceptionMessage = exception.getConstraintViolations()
                .stream()
                .map(v -> getPropertyName(v.getPropertyPath()) + " " + v.getMessage())
                .collect(Collectors.joining("; "));

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", exceptionMessage))
                .build();
    }

    // Private methods
    private String getPropertyName(Path path) {
        // The path has the form of com.package.class.property
        // Split the path by the dot (.) and take the last segment.
        final String[] split = path.toString().split("\\.");
        return split[split.length - 1];
    }

}
