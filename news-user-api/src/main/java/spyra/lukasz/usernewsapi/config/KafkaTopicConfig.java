package spyra.lukasz.usernewsapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Autowired
  private final KafkaTopicNameProvider topicNameProvider;

  public KafkaTopicConfig(final KafkaTopicNameProvider topicNameProvider) {
    this.topicNameProvider = topicNameProvider;
  }

  @Bean
  public NewTopic newsRequestTopic() {
    return TopicBuilder.name(topicNameProvider.newsRequest()).replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic newsResponseTopic() {
    return TopicBuilder.name(topicNameProvider.newsResponse()).replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic jsonTopic() {
    return TopicBuilder.name(topicNameProvider.jsonTopic()).replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic avroTopic() {
    return TopicBuilder.name(topicNameProvider.avroTopic()).replicas(1).partitions(3).build();
  }

}
