package org.limadelrey.quarkus.reactive.rest.api.controller;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;

@Tag(name = "Application health", description = "Endpoints regarding book service health")
@Liveness
@ApplicationScoped
public class HealthCheckController implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Book service").up().build();
    }

}
