package spyra.lukasz.newsconsumer.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import spyra.lukasz.newsconsumer.config.KafkaTopicNameProvider;
import spyra.lukasz.newsconsumer.dto.avro.AvroArticleModel;

@Service
public class AvroMessagePublisher implements AvroMessageService {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaTopicNameProvider topicNameProvider;

  public AvroMessagePublisher(final KafkaTemplate<String, Object> kafkaTemplate, final KafkaTopicNameProvider topicNameProvider) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicNameProvider = topicNameProvider;
  }

  @Override
  public void publishAvroMessage(final AvroArticleModel article) {
    ProducerRecord<String, Object> record = new ProducerRecord<>(topicNameProvider.avroTopic(), article);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", topicNameProvider.avroTopic(), article.getTitle());
    kafkaTemplate.send(record);
    System.out.printf("Extra info from KAFKA topic %s: %s%n", topicNameProvider.avroTopic(), record.value());
  }

}
