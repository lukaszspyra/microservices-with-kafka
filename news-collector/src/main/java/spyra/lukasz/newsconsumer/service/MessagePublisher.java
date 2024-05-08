package spyra.lukasz.newsconsumer.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import spyra.lukasz.newsconsumer.config.KafkaTopicNameProvider;
import spyra.lukasz.newsconsumer.dto.avro.AvroArticleModel;

@Service
public class MessagePublisher implements MessageService {
  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaTopicNameProvider topicNameProvider;

  public MessagePublisher(final KafkaTemplate<String, Object> kafkaTemplate, final KafkaTopicNameProvider topicNameProvider) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicNameProvider = topicNameProvider;
  }

  @Override
  public void publishStringResponseMessage(final String date, final String body) {
    ProducerRecord<String, Object> record = new ProducerRecord<>(topicNameProvider.newsResponse(), date, body);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", record.topic(), body);
    kafkaTemplate.send(record);
    System.out.printf("Pushed message to KAFKA topic %s: %s%n", record.topic(), date);
  }

  @Override
  public void publishAvroResponseMessage(final AvroArticleModel article) {
    ProducerRecord<String, Object> record = new ProducerRecord<>(topicNameProvider.avroTopic(), article);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", record.topic(), article.getTitle());
    kafkaTemplate.send(record);
    System.out.printf("Pushed message to KAFKA topic %s: %s%n", record.topic(), record.value());
  }

}
