package com.nixsolutions.micrometr.service.external.alphavintage;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

public class DomainAwareBuilder {
  private WebClient.RequestBodyUriSpec uriSpec;

  public DomainAwareBuilder(WebClient webClient, HttpMethod method) {
    this.uriSpec = webClient.method(method);
  }

  public DomainAwareBuilder setApiKey(String apiKey) {
    return proceedBuild(UriParams.API_KEY, apiKey);
  }

  public DomainAwareBuilder setContent(String content) {
    return proceedBuild(UriParams.CONTENT, content);
  }

  public DomainAwareBuilder setFunction(String function) {
    return proceedBuild(UriParams.FUNCTION, function);
  }

  public DomainAwareBuilder setInterval(String intervalLength) {
    return proceedBuild(UriParams.INTERVAL, intervalLength);
  }

  public DomainAwareBuilder setSymbol(String symbol) {
    return proceedBuild(UriParams.SYMBOL, symbol);
  }

  private DomainAwareBuilder proceedBuild(String attribute, String value) {
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

  public static DomainAwareBuilder GETDomainAwareBuilder(WebClient webClient) {
    return new DomainAwareBuilder(webClient, HttpMethod.GET);
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