package com.azubike.msscbreweryclient.web.client;

import com.azubike.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreInvalidFields = false)
public class BreweryClient {
  private String apihost;

  public final String BEER_PATH_V1 = "/api/v1/beer/";

  private final RestTemplate restTemplate;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public BeerDto getById(UUID beerId) {
    return restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
  }

  public BeerDto saveNewBeer(BeerDto beerDto) {
    return restTemplate.postForObject(apihost + BEER_PATH_V1, beerDto, BeerDto.class);
  }

  public void setApihost(String apihost) {
    this.apihost = apihost;
  }
}
