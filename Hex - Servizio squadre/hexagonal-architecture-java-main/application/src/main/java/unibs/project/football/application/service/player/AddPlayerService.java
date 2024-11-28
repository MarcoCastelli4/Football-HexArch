package unibs.project.football.application.service.player;

import unibs.project.football.application.port.in.player.AddPlayerUseCase;
import unibs.project.football.application.port.out.persistence.PlayerRepository;
import unibs.project.football.model.player.Player;

public class AddPlayerService implements AddPlayerUseCase {

    private final PlayerRepository playerRepository;


    public AddPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public boolean addPlayer(Player player) {
        if(!playerRepository.getPlayer(player)){
            playerRepository.save(player);
            return true;
        }
        return false;
    }
}

