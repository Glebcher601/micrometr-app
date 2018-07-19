package com.nixsolutions.micrometr.config;

import com.nixsolutions.micrometr.service.handlers.HelloWorldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@Configuration
public class RoutingConfiguration {

  @Bean
  public RouterFunction<ServerResponse> routeExample(HelloWorldHandler exampleHandler) {
    return RouterFunctions
        .route(RequestPredicates.GET("/example")
            .and(RequestPredicates.accept(TEXT_PLAIN)), exampleHandler::hello);
  }
}
