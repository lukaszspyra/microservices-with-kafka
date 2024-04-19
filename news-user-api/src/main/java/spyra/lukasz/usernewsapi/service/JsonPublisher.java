package spyra.lukasz.usernewsapi.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import spyra.lukasz.usernewsapi.config.KafkaTopicNameProvider;
import spyra.lukasz.usernewsapi.dto.Article;

@Service
public class JsonPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaTopicNameProvider topicNameProvider;

  public JsonPublisher(final KafkaTemplate<String, Object> kafkaTemplate, final KafkaTopicNameProvider topicNameProvider) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicNameProvider = topicNameProvider;
  }

  public void publishJsonMessage(final Article article) {
    ProducerRecord<String, Object> record = new ProducerRecord<>(topicNameProvider.jsonTopic(), article);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", topicNameProvider.jsonTopic(), article.getTitle());
    kafkaTemplate.send(record);
  }

}
