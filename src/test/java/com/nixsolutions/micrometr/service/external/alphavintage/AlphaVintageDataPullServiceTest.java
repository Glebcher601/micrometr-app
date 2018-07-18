package com.nixsolutions.micrometr.service.external.alphavintage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.nixsolutions.micrometr.config.ApplicationConfig;

@Configuration
@ComponentScan(value = {"com.nixsolutions.micrometr.service.external"})
@PropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AlphaVintageDataPullServiceTest.class, ApplicationConfig.class})
public class AlphaVintageDataPullServiceTest {
  @Autowired
  private AlphaVintageDataPullService dataPullService;

  @Test
  public void test() throws Exception {
    dataPullService.getIntraDayStockSnapShots();
  }
}