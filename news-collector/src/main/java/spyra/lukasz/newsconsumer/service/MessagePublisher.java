package spyra.lukasz.newsconsumer.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher implements MessageService {
  private final KafkaTemplate<String, String> kafkaTemplate;

  public MessagePublisher(final KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publishResponseMessage(String topic, String date, final String body) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, date, body);
    kafkaTemplate.send(record);
    System.out.printf("Pushed message to KAFKA topic %s: %s%n", topic, date);
  }

}
