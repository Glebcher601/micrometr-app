package com.nixsolutions.micrometr.service.external.alphavintage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DomainAwareWebClientBuilder
{
  @Autowired
  private WebClient webClient;

  @Value("${api.key}")
  private String apiKey;
  @Value("${api.url}")
  private String apiUrl;

  public DomainAwareBuilderv2 newBuilder(HttpMethod method)
  {
    return new DomainAwareBuilderv2(apiUrl, apiKey, method);
  }

}
