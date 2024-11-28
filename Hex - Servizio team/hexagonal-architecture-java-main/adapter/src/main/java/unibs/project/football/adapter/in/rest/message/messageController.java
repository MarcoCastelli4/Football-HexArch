package unibs.project.football.adapter.in.rest.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unibs.project.football.application.port.in.message.MessageUseCase;

@RestController
@RequestMapping("/messages")
public class messageController {

  private final MessageUseCase messageInputPort;

  public messageController(MessageUseCase messageService) {
    this.messageInputPort = messageService;
  }

  @GetMapping("/saveMessage")
  public String saveMessage(@RequestParam("content") String content) {
    messageInputPort.saveMessage(content);
    return "Message saved!: " + content;
  }
}
