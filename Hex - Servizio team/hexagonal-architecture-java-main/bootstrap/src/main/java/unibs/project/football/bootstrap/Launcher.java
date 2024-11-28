package unibs.project.football.bootstrap;

import org.springframework.boot.SpringApplication;
import unibs.project.football.SpringAppConfig;

/** Launcher for the application: starts the Undertow server and deploys the shop application. */
public class Launcher {

  // Qui voglio gestire le squadre
  public static void main(String[] args) {
    SpringApplication.run(SpringAppConfig.class, args);
  }
}
