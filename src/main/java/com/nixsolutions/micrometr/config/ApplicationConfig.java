package com.nixsolutions.micrometr.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nixsolutions.micrometr.model.alphavintage.StockSnapshotsInPeriodDeserializer;
import com.nixsolutions.micrometr.model.alphavintage.StockSnapshotsInPeriod;
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

  @Bean
  public ObjectMapper customMapper(StockSnapshotsInPeriodDeserializer deserializer)
  {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule("Custom deserializer for StockSnapShotsInPeriod",
        new Version(1,0,0,null,null,null));
    module.addDeserializer(StockSnapshotsInPeriod.class, deserializer);
    objectMapper.registerModule(module);

    return objectMapper;
  }
}
