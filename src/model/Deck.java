package model;

import enums.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> originalCards;
    public final List<Card> shuffledCards;

    public Deck() {
        this.originalCards = createFullDeck();
        this.shuffledCards= shuffleCards(originalCards);
    }

    private List<Card> createFullDeck(){
        List<Card> cards = new ArrayList<>();
        Color[] colors = Color.values();

        for(int i = 0; i<=Color.values().length-1;i++){

            Card card = new Card("Ace", 11, colors[i],8,false);
            cards.add(card);
            card = new Card("Ten", 1, colors[i],7,false);
            cards.add(card);
            card = new Card("King", 4, colors[i],6,false);
            cards.add(card);
            card = new Card("Queen", 1, colors[i],5,false);
            cards.add(card);
            card = new Card("Jack", 1, colors[i],4,false);
            cards.add(card);
            card = new Card("Nine", 1, colors[i],3,false);
            cards.add(card);
            card = new Card("Eight", 1, colors[i],2,false);
            cards.add(card);
            card = new Card("Seven", 1, colors[i],1,false);
            cards.add(card);
        }
        return cards;
    }

    private List<Card> shuffleCards(List<Card> originalCards){
        List<Card> shuffled = new ArrayList<Card>(originalCards);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}
