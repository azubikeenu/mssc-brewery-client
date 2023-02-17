package com.azubike.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

  public ClientHttpRequestFactory clientHttpRequestFactory() {
      //Setup a connection factory
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(100); // set the max total connections
    connectionManager.setDefaultMaxPerRoute(20); // set the max connections to a specific route

      // Setup a Request Configurations
    RequestConfig requestConfig =
        RequestConfig.custom().setConnectionRequestTimeout(3000) // Set request timeout to 3secs
                .setSocketTimeout(3000) // Set socket timeout to 3secs
                .build();

    // Implement a closable HttpClient
    CloseableHttpClient closeableHttpClient =
        HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();

    return new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
  }

  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.setRequestFactory(clientHttpRequestFactory());
  }
}
