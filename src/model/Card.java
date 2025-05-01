package model;

import enums.Color;
import java.util.List;

public class Card {
    private String name;
    private Integer value;
    private Color color;
    private Integer power;
    private boolean trump;

    public Card(String name, Integer value, Color color, Integer power, boolean trump) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.power = power;
        this.trump = false;
    }


    public void setTrump(Color color, List<Card> cards){

        for(Card card : cards){
            if(card.color.equals(color))
                card.setTrump(true);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public boolean isTrump() {
        return trump;
    }

    public void setTrump(boolean trump) {
        this.trump = trump;
    }
}
