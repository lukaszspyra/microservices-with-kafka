package spyra.lukasz.usernewsapi.dto;

import lombok.Data;

@Data
public class Article {

  private String author;
  private String title;
  private News news;

  public static class News {
  }

}