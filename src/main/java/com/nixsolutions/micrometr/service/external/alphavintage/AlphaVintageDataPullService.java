package com.nixsolutions.micrometr.service.external.alphavintage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nixsolutions.micrometr.model.alphavintage.StockSnapshot;
import com.nixsolutions.micrometr.model.alphavintage.StockSnapshotsInPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints.TIME_SERIES_INTRADAY;
import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Intervals.FIFTEEN_MINUTES;
import static org.springframework.http.HttpMethod.GET;

@Component
public class AlphaVintageDataPullService {

  @Autowired
  private DomainAwareRequestBuilderFactory domainAwareRequestBuilderFactory;

  @Resource(name = "customMapper")
  private ObjectMapper customMapper;

  public AlphaVintageDataPullService() {

  }

  public List<StockSnapshot> getIntraDayStockSnapShots() throws JsonProcessingException {
    JsonNode msft = methodGETbuilder()
        .setFunction(TIME_SERIES_INTRADAY)
        .setInterval(FIFTEEN_MINUTES)
        .setSymbol("MSFT")
        .buildUriSpec()
        .retrieve()
        .bodyToMono(JsonNode.class)
        .block();

    StockSnapshotsInPeriod stockSnapshotsInPeriod = customMapper.treeToValue(msft, StockSnapshotsInPeriod.class);

    return null;
  }

  public List<StockSnapshot> getDailyStockSnapShots() {
    return null;
  }

  public List<StockSnapshot> getDailyAdjustedStockSnapShots() {
    return null;
  }

  public List<StockSnapshot> getWeeklyStockSnapShots() {
    return null;
  }

  public List<StockSnapshot> getWeeklyAdjustedStockSnapShots() {
    return null;
  }

  public List<StockSnapshot> getMonthlyStockSnapShots() {
    return null;
  }

  public List<StockSnapshot> getMonthlyAdjustedStockSnapShots() {
    return null;
  }

  private DomainAwareRequestBuilder methodGETbuilder() {
    return domainAwareRequestBuilderFactory.newBuilder(GET);
  }
}
