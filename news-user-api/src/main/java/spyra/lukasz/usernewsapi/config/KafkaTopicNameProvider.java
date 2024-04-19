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

  @Value("${spring.kafka.topic.json}")
  private String jsonTopic;

  public String newsRequest() {
    return newsRequest;
  }

  public String jsonTopic() {
    return jsonTopic;
  }

}
