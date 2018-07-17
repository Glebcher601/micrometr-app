package com.nixsolutions.micrometr.service.external.alphavintage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import com.nixsolutions.micrometr.model.alphavintage.TimeSeriesStockSnapshot;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;

import static com.nixsolutions.micrometr.service.external.alphavintage.DomainAwareBuilder.GETDomainAwareBuilder;

public class AlphaVintageDataPullService {
  @Autowired
  private WebClient apiWebClient;

  public AlphaVintageDataPullService() {

  }

  public List<TimeSeriesStockSnapshot> getIntraDayStockSnapShots() {
    GETDomainAwareBuilder(apiWebClient);

    return null;
  }

  public List<TimeSeriesStockSnapshot> getDailyStockSnapShots() {
    return null;
  }

  public List<TimeSeriesStockSnapshot> getDailyAdjustedStockSnapShots() {
    return null;
  }

  public List<TimeSeriesStockSnapshot> getWeeklyStockSnapShots() {
    return null;
  }

  public List<TimeSeriesStockSnapshot> getWeeklyAdjustedStockSnapShots() {
    return null;
  }

  public List<TimeSeriesStockSnapshot> getMonthlyStockSnapShots() {
    return null;
  }

  public List<TimeSeriesStockSnapshot> getMonthlyAdjustedStockSnapShots() {
    return null;
  }


}
