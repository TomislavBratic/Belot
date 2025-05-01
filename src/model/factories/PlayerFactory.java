package model.factories;

import model.Player;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerFactory {

    public static List<Player> createPlayers(String... names){
        return Stream.of(names)
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
