package com.nixsolutions.micrometr.service.external.alphavintage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nixsolutions.micrometr.model.alphavintage.StockSnapshot;
import com.nixsolutions.micrometr.model.alphavintage.StockSnapshotsInPeriod;
import com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints;
import com.nixsolutions.micrometr.service.external.alphavintage.enums.Intervals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints.TIME_SERIES_DAILY;
import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints.TIME_SERIES_INTRADAY;
import static com.nixsolutions.micrometr.service.external.alphavintage.enums.Intervals.FIFTEEN_MINUTES;
import static org.springframework.http.HttpMethod.GET;

@Component
public class AlphaVintageDataPullService {

  @Autowired
  private DomainAwareRequestBuilderFactory domainAwareRequestBuilderFactory;

  @Resource(name = "customMapper")
  private ObjectMapper customMapper;

  public Mono<StockSnapshotsInPeriod> getIntraDayStockSnapShots(Intervals interval, String symbol) {
    return doRequestAndParse(commonUriSpec(TIME_SERIES_INTRADAY, interval, symbol));
  }

  public Mono<StockSnapshotsInPeriod> getDailyStockSnapShots(String symbol) {
    return doRequestAndParse(commonUriSpec(TIME_SERIES_DAILY, null, symbol));
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

  private Mono<StockSnapshotsInPeriod> doRequestAndParse(RequestBodySpec requestBodySpec) {
    return requestBodySpec
        .retrieve()
        .bodyToMono(JsonNode.class)
        .map(node -> uncheckedConversion(node, StockSnapshotsInPeriod.class));
  }

  private RequestBodySpec commonUriSpec(Endpoints endpoint, Intervals interval, String symbol) {
    return methodGETbuilder()
        .setFunction(endpoint)
        .setInterval(interval)
        .setSymbol(symbol)
        .buildUriSpec();
  }

  private <E> E uncheckedConversion(JsonNode node, Class<E> clazz) {
    try {
      return customMapper.treeToValue(node, clazz);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private DomainAwareRequestBuilder methodGETbuilder() {
    return domainAwareRequestBuilderFactory.newBuilder(GET);
  }
}
