package com.nixsolutions.micrometr.service.external.alphavintage;

import com.google.common.collect.ImmutableMap;
import com.nixsolutions.micrometr.service.external.GETApiUrlBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Deprecated
public class AlphaVintageGETApiUrlBuilder implements GETApiUrlBuilder {

  public static class Endpoints {
    public static final String TIME_SERIES_INTRADAY = "TIME_SERIES_INTRADAY";
    public static final String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    public static final String TIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";
    public static final String TIME_SERIES_WEEKLY = "TIME_SERIES_WEEKLY";
    public static final String TIME_SERIES_MONTHLY = "TIME_SERIES_MONTHLY";
  }

  private static final String FUNCTION_PARAM = "function";
  private static final String API_LEY = "apikey";

  @Value("${api.url}")
  private String baseUrl;

  @Value("${api.key}")
  private String apiKey;

  @Override
  public String buildEndpointUrl(Map<String, String> params) {
    return baseUrl +
        ImmutableMap.builder()
        .putAll(params)
        .put(API_LEY, apiKey)
        .build()
        .entrySet()
        .stream()
        .map(entry -> entry.getKey()+"="+entry.getValue())
        .collect(Collectors.joining("&"));
  }

  @Override
  public String buildEndpointUrl(String endPointName, Map<String, String> params) {
    return buildEndpointUrl(ImmutableMap.<String, String>builder()
        .putAll(ImmutableMap.of(FUNCTION_PARAM, endPointName))
        .putAll(params).build());
  }

  @Override
  public String getBaseUrl() {
    return baseUrl;
  }
}
