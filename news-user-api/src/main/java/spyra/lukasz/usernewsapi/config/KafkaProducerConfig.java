package spyra.lukasz.usernewsapi.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.DelegatingByTopicSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import spyra.lukasz.usernewsapi.dto.Article;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  private final KafkaTopicNameProvider nameProvider;

  public KafkaProducerConfig(final KafkaTopicNameProvider nameProvider) {
    this.nameProvider = nameProvider;
  }


  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  public ProducerFactory<String, Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfig(),
        new StringSerializer(),
        new DelegatingByTopicSerializer(Map.of(
            Pattern.compile(nameProvider.jsonTopic()), new JsonSerializer<Article>()),
            new StringSerializer()));  // default;
  }

  public Map<String, Object> producerConfig() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(JsonSerializer.TYPE_MAPPINGS, "ArticleDTO:spyra.lukasz.usernewsapi.dto.Article");
    return props;
  }

}
