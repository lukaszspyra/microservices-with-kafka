package spyra.lukasz.newsconsumer.service;

import reactor.core.publisher.Mono;

public interface MessageService {
  void publishResponseMessage(final String topic, final String date, final String body);
}
