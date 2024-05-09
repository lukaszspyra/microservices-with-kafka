package spyra.lukasz.usernewsapi.config;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class KafkaConsumerConfig {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.schema-registry-servers}")
  private String schemaRegistryServers;

  private final KafkaTopicNameProvider nameProvider;

  public KafkaConsumerConfig(final KafkaTopicNameProvider nameProvider) {
    this.nameProvider = nameProvider;
  }

  public Map<String, Object> consumerConfig() {
    HashMap<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "message-group-2");
    //We set value to true to tell Kafka to use the generated version of generated object. Otherwise, it would deserialize
    // into org.apache.avro.generic.GenericRecord instead of our generated object, which is a SpecificRecord.
    props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
    props.put("value.converter.schema.registry.url", schemaRegistryServers);
    return props;
  }

  public ConsumerFactory<String, Object> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfig(),
        new StringDeserializer(),
        new DelegatingByTopicDeserializer(Map.of(
            Pattern.compile(nameProvider.avroTopic()), new KafkaAvroDeserializer()),
            //example of delegating deserializer choice depending on topic name: Pattern.compile(nameProvider.jsonTopic()), jsonDeserializer),
            new StringDeserializer()));  // default
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}
