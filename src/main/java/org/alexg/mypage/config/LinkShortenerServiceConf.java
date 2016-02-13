package org.alexg.mypage.config;


import org.alexg.mypage.linkshortener.service.verifier.GoogleSafeBrowsingUrlVerifier;
import org.alexg.mypage.linkshortener.service.verifier.UrlVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LinkShortenerServiceConf {
  @Value("${provider.google.api}")
  private String googleApiKey;
  @Value("${provider.google.url}")
  private String googleApiUrl;
  @Value("${provider.google.version}")
  private String googleApiVersion;
  @Value("${info.build.name}")
  private String appName;
  @Value("${info.build.version}")
  private String appVersion;

  @Bean
  public UrlVerifier googleSafetyProvider() {
    GoogleSafeBrowsingConf config = new GoogleSafeBrowsingConf(googleApiKey, googleApiUrl, googleApiVersion, appName, appVersion);
    return new GoogleSafeBrowsingUrlVerifier(restTemplate(), config);
  }

  

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}

