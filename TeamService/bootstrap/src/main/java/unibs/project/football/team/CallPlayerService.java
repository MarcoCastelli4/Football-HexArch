package unibs.project.football.team;

//
// @Configuration
// class CallPlayerServiceConfig {
//
//  @Bean
//  @ConditionalOnProperty(value = "unibs.project.football.team.event", havingValue = "true",
// matchIfMissing = true)
//  public CallPlayerService callPlayerService(OutdoorToPlayerService
// findBestPlayerForTeamController) {
//      return new CallPlayerService(findBestPlayerForTeamController);
//  }
// }
//
// class CallPlayerService {
//
//  public final OutdoorToPlayerService findBestPlayerForTeamController;
//
//  public CallPlayerService(OutdoorToPlayerService findBestPlayerForTeamController) {
//    this.findBestPlayerForTeamController = findBestPlayerForTeamController;
//  }
//
//  @EventListener(ApplicationReadyEvent.class)
//  public void afterApplicationStart() {
//    System.out.println("Spring application started");
//
//
//    System.out.println("Let's consume the PLAYER service");
//
//    var result = findBestPlayerForTeamController.getBestPlayer("Juventus");
//  }
// }
