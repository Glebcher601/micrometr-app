package com.nixsolutions.micrometr.service.external;

import java.util.Map;

@Deprecated
public interface GETApiUrlBuilder extends ApiUrlBuilder {

  String buildEndpointUrl(Map<String, String> params);

  String buildEndpointUrl(String endPointName, Map<String, String> params);
}
