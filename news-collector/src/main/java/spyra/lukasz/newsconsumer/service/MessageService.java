package spyra.lukasz.newsconsumer.service;

import schema.avro.AvroArticleModel;

public interface MessageService {
  void publishStringResponseMessage(final String date, final String body);

  void publishAvroResponseMessage(final AvroArticleModel article);

}
