package spyra.lukasz.usernewsapi.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import spyra.lukasz.usernewsapi.config.KafkaTopicNameProvider;
import spyra.lukasz.usernewsapi.repository.NewsRepository;

@Service
public class MessageRequestService implements MessageService {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final NewsRepository newsRepository;

  private final KafkaTopicNameProvider topicNameProvider;

  public MessageRequestService(KafkaTemplate<String, String> kafkaTemplate, NewsRepository newsRepository, final KafkaTopicNameProvider topicNameProvider) {
    this.kafkaTemplate = kafkaTemplate;
    this.newsRepository = newsRepository;
    this.topicNameProvider = topicNameProvider;
  }

  public Mono<Void> publishToCollectNews(final String date) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topicNameProvider.newsRequest(), null, date);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", topicNameProvider.newsRequest(), date);
    return Mono.fromFuture(kafkaTemplate.send(record))
        .then();
  }

  @Override
  public Mono<Object> getNews(String date) {
    return newsRepository.getNews(date)
        //Mono::just - subscribes / triggers DB call - if present return Hot publisher, otherwise cold publisher
        .flatMap(Mono::just)
        .switchIfEmpty(Mono.defer(() -> publishToCollectNews(date)));
  }

}
