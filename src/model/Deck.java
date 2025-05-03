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
            card = new Card("Ten", 10, colors[i],7,false);
            cards.add(card);
            card = new Card("King", 4, colors[i],6,false);
            cards.add(card);
            card = new Card("Queen", 3, colors[i],5,false);
            cards.add(card);
            card = new Card("Jack", 2, colors[i],4,false);
            cards.add(card);
            card = new Card("Nine", 0, colors[i],3,false);
            cards.add(card);
            card = new Card("Eight", 0, colors[i],2,false);
            cards.add(card);
            card = new Card("Seven", 0, colors[i],1,false);
            cards.add(card);
        }
        return cards;
    }

    private List<Card> shuffleCards(List<Card> originalCards){
        List<Card> shuffled = new ArrayList<Card>(originalCards);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    public void setTrumpCards(List<Card> shuffledCards, Color color){
        shuffledCards.stream()
                .filter(card -> card.getColor() == color)
                .forEach(card -> {
                    switch(card.getName()){
                        case "Jack" ->{
                            card.setValue(20);
                            card.setTrump(true);
                        }
                        case "Nine" ->{
                            card.setValue(14);
                            card.setTrump(true);
                        }
                        case "Ace" ->{
                            card.setValue(11);
                            card.setTrump(true);
                        }
                        case "Ten" ->{
                            card.setValue(10);
                            card.setTrump(true);
                        }
                        case "King" ->{
                            card.setValue(4);
                            card.setTrump(true);
                        }
                        case "Queen" ->{
                            card.setValue(3);
                            card.setTrump(true);
                        }
                        default ->{
                            card.setValue(0);
                            card.setTrump(true);
                        }
                    }
                });
    }
    public void printCards(List <Card> shuffledCards){
        System.out.println("List of Cards: ");
        shuffledCards
                .forEach(card -> {
                    System.out.println(card.getColor()  + " " + card.getName() + " Pw: " + card.getPower() + " Vl: " + card.getValue() + " Tr:" + card.isTrump());
                });

    }
}
