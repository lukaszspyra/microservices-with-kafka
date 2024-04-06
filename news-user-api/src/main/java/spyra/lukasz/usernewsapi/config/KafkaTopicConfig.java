package spyra.lukasz.usernewsapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Value("${spring.kafka.topic.news.request}")
  private String newsRequest;

  @Value("${spring.kafka.topic.news.response}")
  private String newsResponse;

  @Value("${spring.kafka.topic.json}")
  private String jsonTopic;

  @Value("${spring.kafka.topic.avro}")
  private String avroTopic;

  @Bean
  public NewTopic newsRequestTopic() {
    return TopicBuilder.name(newsRequest).replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic newsResponseTopic() {
    return TopicBuilder.name(newsResponse).replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic jsonTopic() {
    return TopicBuilder.name(jsonTopic).replicas(1).partitions(3).build();
  }

  @Bean
  public NewTopic avroTopic() {
    return TopicBuilder.name(avroTopic).replicas(1).partitions(3).build();
  }

}
