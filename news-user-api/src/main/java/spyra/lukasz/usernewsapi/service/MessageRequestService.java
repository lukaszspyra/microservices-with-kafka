package spyra.lukasz.usernewsapi.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import spyra.lukasz.usernewsapi.repository.NewsRepository;

@Service
public class MessageRequestService implements MessageService {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final NewsRepository newsRepository;

  public MessageRequestService(KafkaTemplate<String, String> kafkaTemplate, NewsRepository newsRepository) {
    this.kafkaTemplate = kafkaTemplate;
    this.newsRepository = newsRepository;
  }

  public Mono<Void> publishToCollectNews(final String date) {
    ProducerRecord<String, String> record = new ProducerRecord<>("news", null, date);
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
