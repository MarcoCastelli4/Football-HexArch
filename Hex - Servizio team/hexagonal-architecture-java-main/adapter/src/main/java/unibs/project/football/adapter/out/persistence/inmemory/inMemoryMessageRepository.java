package unibs.project.football.adapter.out.persistence.inmemory;

import java.io.FileWriter;
import java.io.IOException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import unibs.project.football.application.port.out.persistence.MessageRepository;
import unibs.project.football.model.message.Message;

@ConditionalOnProperty(name = "persistence", havingValue = "inmemory", matchIfMissing = true)
@Repository
public class inMemoryMessageRepository implements MessageRepository {

  private static final String FILE_PATH = "messages.txt";

  @Override
  public void save(Message message) {
    try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
      writer.write(message.getContent() + "\n");
    } catch (IOException e) {
      throw new RuntimeException("Failed to write message to file", e);
    }
  }
}
