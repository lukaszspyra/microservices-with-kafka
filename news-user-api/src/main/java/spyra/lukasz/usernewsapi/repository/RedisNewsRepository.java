package spyra.lukasz.usernewsapi.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class RedisNewsRepository implements NewsRepository {

  private final ReactiveRedisOperations<String, Object> redisOperations;

  public RedisNewsRepository(ReactiveRedisOperations<String, Object> redisOperations) {
    this.redisOperations = redisOperations;
  }

  @Override
  public Mono<Object> getNews(String date) {
    return redisOperations.opsForValue().get(date);
  }

  @Override
  public Mono<Boolean> saveNews(String date, Object newsObject) throws JsonProcessingException {
    Duration ttl = Duration.ofHours(1);
    ObjectMapper objectMapper = new ObjectMapper();
    return redisOperations.opsForValue().set(date, objectMapper.readTree(newsObject.toString()), ttl);
  }

}
