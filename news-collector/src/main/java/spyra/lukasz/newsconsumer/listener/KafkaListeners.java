package spyra.lukasz.newsconsumer.listener;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import spyra.lukasz.newsconsumer.config.KafkaTopicNameProvider;
import spyra.lukasz.newsconsumer.service.MessageService;
import spyra.lukasz.newsconsumer.service.WebClientService;

@Component
public class KafkaListeners {

  private final WebClientService webClientService;

  private final MessageService messageService;

  private final KafkaTopicNameProvider topicNameProvider;

  public KafkaListeners(final WebClientService webClientService, final WebClientService webClientService1, final MessageService messageService, final KafkaTopicNameProvider topicNameProvider) {
    this.webClientService = webClientService1;
    this.messageService = messageService;
    this.topicNameProvider = topicNameProvider;
  }


  @KafkaListener(topics = "#{topicNameProvider.newsRequest()}", groupId = "message-group")
  void newsRequestListener(String date) {
    System.out.printf("listener received: %s%n", date);
    Mono<ResponseEntity<String>> responseEntity = webClientService.sendRequest(date);

    responseEntity.subscribe(response -> {
      HttpStatus status = (HttpStatus) response.getStatusCode();
      if (status.equals(HttpStatus.OK)) {
        System.out.println("Data successfully fetched from external API, publishing to string response topic");
        messageService.publishResponseMessage(topicNameProvider.newsResponse(), date, response.getBody());
      } else {
        System.out.println("Data fetch failed");
      }
    });
  }

}
