package com.nixsolutions.micrometr.service.external.alphavintage;

import com.fasterxml.jackson.databind.JsonNode;
import com.nixsolutions.micrometr.model.alphavintage.TimeSeriesStockSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints.TIME_SERIES_INTRADAY;
import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Intervals.FIFTEEN_MINUTES;
import static org.springframework.http.HttpMethod.GET;

@Component
public class AlphaVintageDataPullService {

  @Autowired
  private DomainAwareWebClientBuilder domainAwareWebClientBuilder;

  public AlphaVintageDataPullService() {

  }

  public List<TimeSeriesStockSnapshot> getIntraDayStockSnapShots() {
    JsonNode msft = methodGETbuilder()
            .setFunction(TIME_SERIES_INTRADAY)
            .setInterval(FIFTEEN_MINUTES)
            .setSymbol("MSFT")
            .buildUriSpec()
            .retrieve()
            .bodyToMono(JsonNode.class)
            .block();
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

  private DomainAwareBuilderv2 methodGETbuilder() {
    return domainAwareWebClientBuilder.newBuilder(GET);
  }
}
