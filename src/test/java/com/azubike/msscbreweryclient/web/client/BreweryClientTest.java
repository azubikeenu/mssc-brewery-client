package com.azubike.msscbreweryclient.web.client;

import com.azubike.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BreweryClientTest {
  @Autowired BreweryClient breweryClient;

  @Test
  void getById() {
    final BeerDto beerDto = breweryClient.getById(UUID.randomUUID());
    assertThat(beerDto).isNotNull();
  }

  @Test
  void saveNewBeer() {
    final BeerDto newBeer = BeerDto.builder().beerName("Star").build();
    final BeerDto savedBeer = breweryClient.saveNewBeer(newBeer);
    assertThat(savedBeer).isNotNull();
    assertThat(savedBeer.getBeerName()).isEqualTo(newBeer.getBeerName());
  }

  @Test
  void updateExistingBeer() {
    final BeerDto updatedBeer = BeerDto.builder().beerName("Star").build();
    breweryClient.updateExistingBeer(UUID.randomUUID(), updatedBeer);
  }

  @Test
  void deleteExistingBeer(){
    breweryClient.deleteExistingBeer(UUID.randomUUID());
  }
}
