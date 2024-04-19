package spyra.lukasz.newsconsumer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Article {

  public String author;
  public String title;
  public News news;

  @JsonCreator
  public Article(@JsonProperty("author")final String author, @JsonProperty("title")final String title, @JsonProperty("news")News news) {
    this.author = author;
    this.title = title;
    this.news = news;
  }

  public static class News {
    public final String content;

    @JsonCreator
    News(@JsonProperty("content")final String content) {
      this.content = content;
    }

    @Override
    public String toString() {
      return "News{" +
          "content='" + content + '\'' +
          '}';
    }

  }

  @Override
  public String toString() {
    return "Article{" +
        "author='" + author + '\'' +
        ", title='" + title + '\'' +
        ", news=" + news +
        '}';
  }

}
