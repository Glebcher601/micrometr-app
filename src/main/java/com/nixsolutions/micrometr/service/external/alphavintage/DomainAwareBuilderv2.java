package com.nixsolutions.micrometr.service.external.alphavintage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DomainAwareBuilderv2 {
  private WebClient.RequestBodyUriSpec uriSpec;
  private WebClient.Builder webClientBuilder;
  @Autowired
  private WebClient webClient;

  public DomainAwareBuilderv2 newInstance(HttpMethod method) {
  }

  public DomainAwareBuilderv2 setApiKey(String apiKey) {
    return proceedBuild(UriParams.API_KEY, apiKey);
  }

  public DomainAwareBuilderv2 setContent(String content) {
    return proceedBuild(UriParams.CONTENT, content);
  }

  public DomainAwareBuilderv2 setFunction(String function) {
    return proceedBuild(UriParams.FUNCTION, function);
  }

  public DomainAwareBuilderv2 setInterval(String intervalLength) {
    return proceedBuild(UriParams.INTERVAL, intervalLength);
  }

  public DomainAwareBuilderv2 setSymbol(String symbol) {
    return proceedBuild(UriParams.SYMBOL, symbol);
  }

  private DomainAwareBuilderv2 proceedBuild(String attribute, String value) {
    checkAttributeAndSet(attribute, value);
    return this;
  }

  private void checkAttributeAndSet(String attribute, String value) {
    failIfAttributeIsPresent(attribute);
    uriSpec.attribute(attribute, value);
  }

  private void failIfAttributeIsPresent(String attribute) {
    uriSpec.attributes(attrs -> {
      if (attrs.containsKey(attribute))
        throw new IllegalStateException("URI specification is incorrect, no duplicating params allowed");
    });
  }

  public WebClient.RequestBodyUriSpec build() {
    return uriSpec;
  }

  public static class UriParams {
    public static final String FUNCTION = "function";
    public static final String API_KEY = "apikey";
    public static final String INTERVAL = "interval";
    public static final String SYMBOL = "symbol";
    public static final String CONTENT = "content";
  }

  public static class EndPoints {
    public static final String TIME_SERIES_INTRADAY = "TIME_SERIES_INTRADAY";
    public static final String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    public static final String TIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";
    public static final String TIME_SERIES_WEEKLY = "TIME_SERIES_WEEKLY";
    public static final String TIME_SERIES_MONTHLY = "TIME_SERIES_MONTHLY";
  }


}
