package eu.happycoders.shop.adapter.in.rest.message;

import eu.happycoders.shop.application.port.in.message.messageUseCase;
import eu.happycoders.shop.application.service.message.messageService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;



@Path("/messages")
public class messageController {

    private final messageUseCase messageInputPort;

    public messageController(messageUseCase messageService) {
        this.messageInputPort = messageService;
    }

    @GET
    @Path("/saveMessage")
    public String saveMessage(@QueryParam("content") String content) {
        messageInputPort.saveMessage(content);
        return "Message saved!: " + content;
    }
}


