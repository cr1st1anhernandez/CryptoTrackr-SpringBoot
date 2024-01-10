package com.cristian.CryptoTrackr.controllers;

import com.cristian.CryptoTrackr.services.CoinGeckoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {

  private final CoinGeckoService coinGeckoService;

  @Autowired
  public CryptoController(CoinGeckoService coinGeckoService) {
    this.coinGeckoService = coinGeckoService;
  }

  @GetMapping("/crypto/status")
  public String getApiStatus() {
    return coinGeckoService.getApiStatus();
  }

  @GetMapping("/crypto/trending")
  public String getTrendingCoins() {
    return coinGeckoService.getTrendingCoins();
  }

  @GetMapping("/crypto/news")
  public String getNews() {
    return coinGeckoService.getNews();
  }

  @GetMapping("/crypto/coin/{coinName}")
  @ResponseBody
  public String getCoinInfo(@PathVariable String coinName){
    return coinGeckoService.getCoinInfo(coinName);
  }
}
