package unibs.project.football.application.service.message;

import unibs.project.football.application.port.in.message.messageUseCase;
import unibs.project.football.application.port.out.persistence.messageRepository;
import unibs.project.football.model.message.Message;

public class messageService implements messageUseCase {

  private final messageRepository messageRepository;

  public messageService(messageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public void saveMessage(String content) {
    Message message = new Message(content);
    messageRepository.save(message);
  }
}
