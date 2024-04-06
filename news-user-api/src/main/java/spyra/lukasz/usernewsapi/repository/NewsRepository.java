package spyra.lukasz.usernewsapi.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

public interface NewsRepository {
  Mono<Object> getNews(String date);

  Mono<Boolean> saveNews(String date, Object newsObject) throws JsonProcessingException;

}
