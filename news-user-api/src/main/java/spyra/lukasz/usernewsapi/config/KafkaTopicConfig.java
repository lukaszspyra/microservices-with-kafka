package spyra.lukasz.usernewsapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
  @Bean
  public NewTopic newsTopic() {
    return TopicBuilder.name("news").replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic savedToCacheTopic() {
    return TopicBuilder.name("news-cache").replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic jsonTopic() {
    return TopicBuilder.name("json-topic").replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic avroTopic() {
    return TopicBuilder.name("avro-topic").replicas(1).partitions(3).build();
  }

}
