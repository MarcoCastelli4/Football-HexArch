package unibs.project.football.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Launcher for the application: starts the Undertow server and deploys the shop application. */
@SpringBootApplication
public class Launcher {

  // Gestisco i player

  // https://www.baeldung.com/spring-cloud-contract
  public static void main(String[] args) {

    SpringApplication.run(SpringAppConfig.class, args);
  }
}
