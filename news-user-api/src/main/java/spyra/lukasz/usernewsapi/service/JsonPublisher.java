package spyra.lukasz.usernewsapi.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import spyra.lukasz.usernewsapi.config.KafkaTopicNameProvider;
import spyra.lukasz.usernewsapi.dto.Article;

import java.util.concurrent.CompletableFuture;

@Service
public class JsonPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaTopicNameProvider topicNameProvider;

  public JsonPublisher(final KafkaTemplate<String, Object> kafkaTemplate, final KafkaTopicNameProvider topicNameProvider) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicNameProvider = topicNameProvider;
  }

  public CompletableFuture<Object> publishJsonMessage(final Article article) {
    ProducerRecord<String, Object> record = new ProducerRecord<>(topicNameProvider.jsonTopic(), article);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", topicNameProvider.jsonTopic(), article.getTitle());
    return kafkaTemplate.send(record).handle(((result, ex) -> {
      if (ex == null) {
        System.out.printf("Pushed message %s, to KAFKA topic %s%n",
            result.getProducerRecord().value(),
            result.getRecordMetadata().topic());
        return result;
      } else {
        System.out.printf("Unable to send message to KAFKA topic %s, due to: %s%n", record.topic(), ex.getMessage());
        return null;
      }
    }));
  }

}
