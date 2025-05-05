package model;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import model.Card;

public class Player {
    private final String name;
    public final List<Card> hand;

    public Player(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Player name can not be empty!");
        }
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public String getName() {
        return name;
    }

    public void addToHand( Card card)
    {
        hand.add(card);
    }

    public Card playCard(int index){
        return hand.remove(index);
    }
}
