package unibs.project.football.team;

import org.springframework.boot.SpringApplication;

/** Launcher for the application: starts the Undertow server and deploys the shop application. */
public class Launcher {

  // Qui voglio gestire le squadre
  public static void main(String[] args) {
    SpringApplication.run(SpringAppConfig.class, args);
  }
}
