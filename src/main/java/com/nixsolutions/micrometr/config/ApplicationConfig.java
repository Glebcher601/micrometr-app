package com.nixsolutions.micrometr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig
{
  @Bean
  public WebClient apiWebClient(@Value("${api.url}") String baseUrl)
  {
    return WebClient.builder().baseUrl(baseUrl).build();
  }
}
