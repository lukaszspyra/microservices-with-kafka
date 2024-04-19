package spyra.lukasz.usernewsapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import spyra.lukasz.usernewsapi.dto.Article;
import spyra.lukasz.usernewsapi.dto.response.DataResponse;
import spyra.lukasz.usernewsapi.service.JsonPublisher;
import spyra.lukasz.usernewsapi.service.MessageService;

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

  @GetMapping
  public String healthCheck() {
    return "healthy";
  }

  @GetMapping("/news-json")
  public void publishJson() {
    Article article = new Article("Some test Author", "Some test Title", "Exciting test news");
    publisher.publishJsonMessage(article);
  }

}
