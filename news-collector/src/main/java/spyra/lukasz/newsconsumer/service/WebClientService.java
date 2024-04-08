package spyra.lukasz.newsconsumer.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface WebClientService {
  Mono<ResponseEntity<String>> sendRequest(String date);
}
