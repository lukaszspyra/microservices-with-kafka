package spyra.lukasz.usernewsapi.dto;

import lombok.Data;

@Data
public class Article {

  private String author;
  private String title;
  private News news;

  public Article(final String author, final String title, String content) {
    this.author = author;
    this.title = title;
    news = new News(content);
  }

  private static class News {
    private final String content;

    News(final String content) {
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
