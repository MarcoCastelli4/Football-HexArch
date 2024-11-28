package unibs.project.football.application.port.out.persistence;

import unibs.project.football.model.message.Message;

public interface MessageRepository {
  void save(Message message);
}
