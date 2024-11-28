package unibs.project.football.application.service.message;

import unibs.project.football.application.port.in.message.MessageUseCase;
import unibs.project.football.application.port.out.persistence.MessageRepository;
import unibs.project.football.model.message.Message;

public class messageService implements MessageUseCase {

  private final MessageRepository messageRepository;

  public messageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public void saveMessage(String content) {
    Message message = new Message(content);
    messageRepository.save(message);
  }
}
