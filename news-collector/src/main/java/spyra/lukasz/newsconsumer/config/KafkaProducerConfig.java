package spyra.lukasz.newsconsumer.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.DelegatingByTopicSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class KafkaProducerConfig {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.schema-registry-servers}")
  private String schemaRegistryServers;

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
            Pattern.compile(nameProvider.avroTopic()), new KafkaAvroSerializer()),
           // example of another kind of topic: Pattern.compile(nameProvider.jsonTopic()), new JsonSerializer<Article>()
            new StringSerializer()));  // default;
  }

  /**
   * Create map with producer configuration
   * <p>
   * In case of single formats we can use:
   * props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
   * props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
   * otherwise, we can delegate allocating serializers to topic at runtime by using c-tor of {@link DefaultKafkaProducerFactory},
   * as used in {@link KafkaProducerConfig#producerFactory()}
   *
   * @return
   */
  @Bean
  public Map<String, Object> producerConfig() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put("schema.registry.url", schemaRegistryServers);
    return props;
  }

}
