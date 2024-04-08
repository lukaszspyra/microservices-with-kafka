package spyra.lukasz.usernewsapi.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import spyra.lukasz.usernewsapi.config.KafkaTopicNameProvider;
import spyra.lukasz.usernewsapi.repository.NewsRepository;

@Component
public class KafkaListeners {

  @Value("${spring.kafka.topic.news.response}")
  private String newsResponse;

  private final NewsRepository newsRepository;
  private final KafkaTopicNameProvider topicNameProvider;

  public KafkaListeners(final NewsRepository newsRepository, final KafkaTopicNameProvider topicNameProvider) {
    this.newsRepository = newsRepository;
    this.topicNameProvider = topicNameProvider;
  }


  @KafkaListener(topics = "#{topicNameProvider.newsResponse()}", groupId = "message-group-2")
  void newsResponseListener(ConsumerRecord<String, String> consumerRecord) {
    System.out.printf("cached listener received: %s%n", consumerRecord.key());
    try {
      newsRepository.saveNews(consumerRecord.key(), consumerRecord.value())
          .subscribe(isSaved -> {
            if (isSaved) {
              System.out.println("Data successfully saved in database");
            } else {
              System.out.println("Data save failed");
            }
          });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
