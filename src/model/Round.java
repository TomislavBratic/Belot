package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Round {
    private final int round;
    private Player winner;
    private Map<Player,Card> plays = new LinkedHashMap<>();

    public Round(int round){
        this.round = round;
    }
}
