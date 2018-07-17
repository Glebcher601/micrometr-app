package com.nixsolutions.micrometr;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;

@Configuration
@ComponentScan(value = "com.nixsolutions.micrometr.service.external")
@PropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebClientPlayGround.class)
public class WebClientPlayGround
{


  @Value("${api.key}")
  private String apikey;

  @Test
  public void simpleTest()
  {


    WebClient webClient = WebClient.builder()
        .baseUrl("")
        .build();


    JsonNode node = webClient.get()
        .retrieve()
        .bodyToMono(JsonNode.class)
        .block();
  }

}
