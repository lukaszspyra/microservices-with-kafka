package spyra.lukasz.newsconsumer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientNewsRequest implements WebClientService {

  @Value("${newsapi.api-key}")
  private String apiKey;

  @Value("${newsapi.keyword}")
  private String keyword;

  private final WebClient webClient;

  public WebClientNewsRequest(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<ResponseEntity<String>> sendRequest(String date) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("apiKey", apiKey)
            .queryParam("q", keyword)
            .queryParam("from", date)
            .build())
        .retrieve()
        .toEntity(String.class);
  }

}
