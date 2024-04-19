package spyra.lukasz.usernewsapi.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import spyra.lukasz.usernewsapi.config.KafkaTopicNameProvider;
import spyra.lukasz.usernewsapi.dto.Article;

public class JsonPublisher {

  private final KafkaTemplate<String, Article> kafkaTemplate;
  private final KafkaTopicNameProvider topicNameProvider;

  public JsonPublisher(final KafkaTemplate<String, Article> kafkaTemplate, final KafkaTopicNameProvider topicNameProvider) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicNameProvider = topicNameProvider;
  }

  public void publishJsonMessage(final Article article) {
    ProducerRecord<String, Article> record = new ProducerRecord<>(topicNameProvider.jsonTopic(), article);
    kafkaTemplate.send(record);
    System.out.printf("Pushed message to KAFKA topic %s: %s%n", topicNameProvider.jsonTopic(), article.getTitle());
  }

}
