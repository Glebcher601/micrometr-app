package com.nixsolutions.micrometr.service.external.alphavintage;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import com.nixsolutions.micrometr.service.external.alphavintage.enums.Endpoints;
import com.nixsolutions.micrometr.service.external.alphavintage.enums.Intervals;
import com.sun.beans.editors.EnumEditor;

public class DomainAwareBuilderv2 {
  private WebClient.RequestBodyUriSpec bodyUriSpec;
  private UriComponentsBuilder uriBuilder;
  private Map<String, String> attrValueMap;

  private DomainAwareBuilderv2(String apiUrl, HttpMethod method) {
    attrValueMap = new LinkedHashMap<>();
    bodyUriSpec = WebClient.create().method(method);
    uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
  }

  DomainAwareBuilderv2(String apiUrl, String apiKey, HttpMethod method) {
    this(apiUrl, method);
    setApiKey(apiKey);
  }

  public DomainAwareBuilderv2 setApiKey(String apiKey) {
    return proceedBuild(UriParams.API_KEY, apiKey);
  }

  public DomainAwareBuilderv2 setContent(String content) {
    return proceedBuild(UriParams.CONTENT, content);
  }

  public DomainAwareBuilderv2 setFunction(Endpoints function) {
    return proceedBuild(UriParams.FUNCTION, function.getValue());
  }

  public DomainAwareBuilderv2 setInterval(Intervals intervalLength) {
    return proceedBuild(UriParams.INTERVAL, intervalLength.getValue());
  }

  public DomainAwareBuilderv2 setSymbol(String symbol) {
    return proceedBuild(UriParams.SYMBOL, symbol);
  }

  private DomainAwareBuilderv2 proceedBuild(String attribute, String value) {
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
    public static final String CONTENT = "content";
  }
}
