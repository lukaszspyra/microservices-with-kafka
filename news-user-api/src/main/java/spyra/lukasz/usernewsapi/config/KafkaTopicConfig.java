package spyra.lukasz.usernewsapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Creates topics for publishing
 */
@Configuration
public class KafkaTopicConfig {

  private final KafkaTopicNameProvider topicNameProvider;

  @Autowired
  public KafkaTopicConfig(final KafkaTopicNameProvider topicNameProvider) {
    this.topicNameProvider = topicNameProvider;
  }

  @Bean
  public NewTopic newsRequestTopic() {
    return TopicBuilder.name(topicNameProvider.newsRequest()).replicas(3).partitions(3).build();
  }

  @Bean
  public NewTopic newsJsonTopic() {
    return TopicBuilder.name(topicNameProvider.jsonTopic()).replicas(3).partitions(3).build();
  }

}
