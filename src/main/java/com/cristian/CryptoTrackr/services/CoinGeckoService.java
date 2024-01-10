package com.cristian.CryptoTrackr.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CoinGeckoService {
  private final WebClient webClient;
  private final WebClient newsWebClient;

  @Value("${API_KEY_SECRET}")
  private String apiKeySecret;

  public CoinGeckoService() {
    this.webClient = WebClient.create("https://api.coingecko.com/api/v3");
    this.newsWebClient = WebClient.create("https://newsdata.io/api/1/");
  }

  public String getApiStatus() {
    return webClient.get()
        .uri("/ping")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }

  public String getTrendingCoins() {
    return webClient.get()
        .uri("/search/trending")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }

  public String getNews() {
    return newsWebClient.get()
        .uri("/news?apikey=" + apiKeySecret + "&language=en")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }

  public String getCoinInfo(String coinName) {
    return webClient.get()
        .uri("/coins/markets?vs_currency=usd&ids=" + coinName
            + "&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }
}
