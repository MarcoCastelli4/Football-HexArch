package unibs.project.football.adapter.in.rest.message;

import unibs.project.football.application.port.in.message.messageUseCase;
import jakarta.ws.rs.GET;
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
