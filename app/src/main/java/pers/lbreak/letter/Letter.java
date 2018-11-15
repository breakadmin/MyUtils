package pers.lbreak.letter;

/**
 * 字母
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
