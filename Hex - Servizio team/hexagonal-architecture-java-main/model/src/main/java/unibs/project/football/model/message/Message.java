package unibs.project.football.model.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@RequiredArgsConstructor
public class Message {

  @Getter private String content;

  public Message(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
