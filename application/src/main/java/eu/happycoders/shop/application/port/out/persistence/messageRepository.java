package eu.happycoders.shop.application.port.out.persistence;

import eu.happycoders.shop.model.message.Message;

public interface messageRepository {
    void save(Message message);
}
