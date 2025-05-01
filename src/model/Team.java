package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> members = new ArrayList<>();
    private Integer score;

    public Team(String name, Player player1, Player player2){
        this.members.add(player1);
        this.members.add(player2);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Player> getMembers() {
        return members;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer points) {
        this.score += points;
    }
}
