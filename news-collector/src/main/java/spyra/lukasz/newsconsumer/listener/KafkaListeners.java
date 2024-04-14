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

  private final KafkaTopicNameProvider kafkaTopicNameProvider;

  public KafkaListeners(final WebClientService webClientService, final MessageService messageService, final KafkaTopicNameProvider kafkaTopicNameProvider) {
    this.webClientService = webClientService;
    this.messageService = messageService;
    this.kafkaTopicNameProvider = kafkaTopicNameProvider;
  }


  @KafkaListener(topics = {"#{kafkaTopicNameProvider.newsRequest()}"}, groupId = "message-group")
  void newsRequestListener(String date) {
    System.out.printf("KAFKA request listener received: %s%n", date);
    Mono<ResponseEntity<String>> responseEntity = webClientService.sendRequest(date);

    responseEntity.subscribe(response -> {
      HttpStatus status = (HttpStatus) response.getStatusCode();
      if (status.equals(HttpStatus.OK)) {
        System.out.println("Data successfully fetched from external API, publishing to string response topic");
        messageService.publishResponseMessage(kafkaTopicNameProvider.newsResponse(), date, response.getBody());
      } else {
        System.out.println("Data fetch failed");
      }
    });
  }

}
