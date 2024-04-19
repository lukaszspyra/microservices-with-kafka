package spyra.lukasz.usernewsapi.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.DelegatingByTopicDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import spyra.lukasz.usernewsapi.dto.Article;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class KafkaConsumerConfig {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.topic.news.response}")
  private String newsResponse;

  @Value("${spring.kafka.topic.json}")
  private String jsonTopic;

  @Value("${spring.kafka.topic.avro}")
  private String avroTopic;

  private final KafkaTopicNameProvider nameProvider;

  public KafkaConsumerConfig(final KafkaTopicNameProvider nameProvider) {
    this.nameProvider = nameProvider;
  }

  public Map<String, Object> consumerConfig() {
    HashMap<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "message-group-2");
    return props;
  }

  public ConsumerFactory<String, Object> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfig(),
        new StringDeserializer(),
        new DelegatingByTopicDeserializer(Map.of(
            Pattern.compile(nameProvider.jsonTopic()), new JsonDeserializer<Article>()),
            new StringDeserializer()));  // default
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}
