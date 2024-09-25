package eu.happycoders.shop.adapter.out.persistence.inmemory;

import eu.happycoders.shop.application.port.out.persistence.messageRepository;
import eu.happycoders.shop.model.message.Message;

import java.io.FileWriter;
import java.io.IOException;

public class inMemoryMessageRepository implements messageRepository {

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
