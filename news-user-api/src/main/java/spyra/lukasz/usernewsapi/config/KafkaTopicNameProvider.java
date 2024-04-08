package spyra.lukasz.usernewsapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Provides kafka topic names
 */
@Component
public class KafkaTopicNameProvider {

  @Value("${spring.kafka.topic.news.request}")
  private String newsRequest;

  @Value("${spring.kafka.topic.news.response}")
  private String newsResponse;

  @Value("${spring.kafka.topic.json}")
  private String jsonTopic;

  @Value("${spring.kafka.topic.avro}")
  private String avroTopic;

  String newsRequest() {
    return newsRequest;
  }

  String newsResponse() {
    return newsResponse;
  }

  String jsonTopic() {
    return jsonTopic;
  }

  String avroTopic() {
    return avroTopic;
  }

}
