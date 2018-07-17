package com.nixsolutions.micrometr.service.handlers;

import com.nixsolutions.micrometr.model.TestObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class HelloWorldHandler {

  public Mono<ServerResponse> hello(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromObject(new TestObject("Hello world!")));
  }
}
