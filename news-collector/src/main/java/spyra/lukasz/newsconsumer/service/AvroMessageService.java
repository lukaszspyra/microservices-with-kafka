package spyra.lukasz.newsconsumer.service;

import spyra.lukasz.newsconsumer.dto.avro.AvroArticleModel;

public interface AvroMessageService {
  void publishAvroMessage(final AvroArticleModel article);

}
