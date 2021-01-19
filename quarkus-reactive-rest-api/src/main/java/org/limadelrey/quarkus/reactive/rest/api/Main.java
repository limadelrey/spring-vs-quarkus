package org.limadelrey.quarkus.reactive.rest.api;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Quarkus Reactive REST API",
                version = "1.0.0")
)
public class Main extends Application {

}
