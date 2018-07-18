package com.nixsolutions.micrometr.service.external.alphavintage;

import com.nixsolutions.micrometr.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;

@Configuration
@ComponentScan(value = {"com.nixsolutions.micrometr.service.external"})
@PropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DomainAwareBuilderv2Test.class, ApplicationConfig.class})
public class DomainAwareBuilderv2Test
{
  @Autowired
  private DomainAwareWebClientBuilder domainAwareWebClientBuilder;

  @Test
  public void testSingleParam() throws Exception
  {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("function", "INTRADAY");

    assertEquals("?function=INTRADAY", builder.build().toUriString());
  }

  @Test
  public void testMultipleParam() throws Exception
  {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("function", "INTRADAY").queryParam("symbol", "MSFT");

    assertEquals("?function=INTRADAY&symbol=MSFT", builder.build().toUriString());
  }

}