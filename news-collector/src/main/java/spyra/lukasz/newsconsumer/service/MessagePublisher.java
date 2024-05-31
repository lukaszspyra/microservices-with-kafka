package spyra.lukasz.newsconsumer.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import schema.avro.AvroArticleModel;
import spyra.lukasz.newsconsumer.config.KafkaTopicNameProvider;


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
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", record.topic(), record.value());
    kafkaTemplate.send(record).whenComplete(((result, ex) -> {
      if (ex == null) {
        System.out.printf("Pushed message %s, to KAFKA topic %s, dated: %s%n",
            result.getProducerRecord().value(),
            result.getRecordMetadata().topic(), date);
      } else {
        System.out.printf("Unable to send message to KAFKA topic %s, due to: %s%n", record.topic(), ex.getMessage());
      }
    }));
  }

  @Override
  public void publishAvroResponseMessage(final AvroArticleModel article) {
    ProducerRecord<String, Object> record = new ProducerRecord<>(topicNameProvider.avroTopic(), article);
    System.out.printf("Pushing message to KAFKA topic %s: %s%n", record.topic(), record.value());
    kafkaTemplate.send(record).whenComplete(((result, ex) -> {
      if (ex == null) {
        System.out.printf("Pushed message %s, to KAFKA topic %s%n",
            result.getProducerRecord().value(),
            result.getRecordMetadata().topic());
      } else {
        System.out.printf("Unable to send message to KAFKA topic %s, due to: %s%n", record.topic(), ex.getMessage());
      }
    }));
  }

}
