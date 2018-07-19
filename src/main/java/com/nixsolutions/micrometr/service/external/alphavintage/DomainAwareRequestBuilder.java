package com.nixsolutions.micrometr.service.external.alphavintage;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints;
import com.nixsolutions.micrometr.service.external.alphavintage.enums.Intervals;

public class DomainAwareRequestBuilder {
  private WebClient.RequestBodyUriSpec bodyUriSpec;
  private UriComponentsBuilder uriBuilder;
  private Map<String, String> attrValueMap;

  private DomainAwareRequestBuilder(String apiUrl, HttpMethod method) {
    attrValueMap = new LinkedHashMap<>();
    bodyUriSpec = WebClient.create().method(method);
    uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
  }

  DomainAwareRequestBuilder(String apiUrl, String apiKey, HttpMethod method) {
    this(apiUrl, method);
    setApiKey(apiKey);
  }

  public DomainAwareRequestBuilder setApiKey(String apiKey) {
    return proceedBuild(UriParams.API_KEY, apiKey);
  }

  public DomainAwareRequestBuilder setOutputSize(String outputSize) {
    return proceedBuild(UriParams.OUTPUT_SIZE, outputSize);
  }

  public DomainAwareRequestBuilder setFunction(Endpoints function) {
    return proceedBuild(UriParams.FUNCTION, function.getValue());
  }

  public DomainAwareRequestBuilder setInterval(Intervals intervalLength) {
    return proceedBuild(UriParams.INTERVAL, intervalLength.getValue());
  }

  public DomainAwareRequestBuilder setSymbol(String symbol) {
    return proceedBuild(UriParams.SYMBOL, symbol);
  }

  private DomainAwareRequestBuilder proceedBuild(String attribute, String value) {
    attrValueMap.putIfAbsent(attribute, value);
    return this;
  }

  public WebClient.RequestBodySpec buildUriSpec() {
    attrValueMap.forEach((key, value) -> uriBuilder.queryParam(key, value));
    return bodyUriSpec.uri(uriBuilder.build(new Object()));
  }

  private static class UriParams {
    public static final String FUNCTION = "function";
    public static final String API_KEY = "apikey";
    public static final String INTERVAL = "interval";
    public static final String SYMBOL = "symbol";
    public static final String OUTPUT_SIZE = "outputsize";
  }
}
