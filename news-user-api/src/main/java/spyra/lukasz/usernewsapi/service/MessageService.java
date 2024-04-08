package spyra.lukasz.usernewsapi.service;

import reactor.core.publisher.Mono;

public interface MessageService {
  Mono<Object> getNews(String date);

}
