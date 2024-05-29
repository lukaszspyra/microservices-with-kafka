package spyra.lukasz.usernewsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import spyra.lukasz.usernewsapi.dto.Article;
import spyra.lukasz.usernewsapi.dto.response.DataResponse;
import spyra.lukasz.usernewsapi.service.JsonPublisher;
import spyra.lukasz.usernewsapi.service.MessageService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public class MessageController {
  private final MessageService service;
  private final JsonPublisher publisher;

  public MessageController(MessageService service, final JsonPublisher publisher) {
    this.service = service;
    this.publisher = publisher;
  }

  @GetMapping("/news")
  public Mono<ResponseEntity<DataResponse<Object>>> getNews(@RequestParam(name = "date") String date) {
    return service.getNews(date)
        .flatMap(data -> Mono.just(
            ResponseEntity.status(HttpStatus.OK)
                .body(new DataResponse<>
                    ("data found", true, data))))
        .switchIfEmpty(Mono.defer(() -> Mono.just(
            ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new DataResponse<>
                    ("data not found, sending request to broker", false, null)))));
  }

  @GetMapping("/health")
  public ResponseEntity<DataResponse<Object>> healthCheck() {
    return ResponseEntity.status(HttpStatus.OK).
        body(new DataResponse<>
            ("Healthy", true, null));
  }

  @PostMapping("/news-json")
  public ResponseEntity<DataResponse<Object>> publishJson(@RequestBody Article article) {
    final CompletableFuture<Object> result = publisher.publishJsonMessage(article);
    return result != null? ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>
            ("Publish json message successful", true, result)) :
        ResponseEntity.status(HttpStatus.NO_CONTENT).body(new DataResponse<>
            ("Exception during publishing Json message", true, null));
  }

}
