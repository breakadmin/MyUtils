package pers.lbreak.letter;

/**
 * Created by 惠普 on 2018-05-07.
 */

public class Letter {

    String Letter;
    boolean isHover=false;

    public String getLetter() {
        return Letter;
    }

    public void setLetter(String letter) {
        Letter = letter;
    }

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

}
