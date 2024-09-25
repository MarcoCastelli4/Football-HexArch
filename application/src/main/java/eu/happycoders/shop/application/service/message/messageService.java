package eu.happycoders.shop.application.service.message;

import eu.happycoders.shop.application.port.in.message.messageUseCase;
import eu.happycoders.shop.application.port.out.persistence.messageRepository;
import eu.happycoders.shop.model.message.Message;

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
