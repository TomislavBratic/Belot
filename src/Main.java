import model.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       Game game = new Game("Bob","Alice","Rob","Katy");
        System.out.println(game.getTeamMembers());
        System.out.println(game.getPlayerNames());
        System.out.println(game.getPlayers());
        game.chooseTrump();

    }

}