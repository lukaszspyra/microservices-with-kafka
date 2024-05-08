package spyra.lukasz.newsconsumer.service;

import spyra.lukasz.newsconsumer.dto.avro.AvroArticleModel;

public interface MessageService {
  void publishStringResponseMessage(final String date, final String body);

  void publishAvroResponseMessage(final AvroArticleModel article);

}
