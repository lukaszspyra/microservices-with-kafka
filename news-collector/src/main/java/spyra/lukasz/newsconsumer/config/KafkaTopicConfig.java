package spyra.lukasz.newsconsumer.config;

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
  public NewTopic newsResponseTopic() {
    return TopicBuilder.name(topicNameProvider.newsResponse()).replicas(2).partitions(2).build();
  }

  @Bean
  public NewTopic avroNewsResponseTopic() {
    return TopicBuilder.name(topicNameProvider.avroTopic()).replicas(2).partitions(2).build();
  }

}
