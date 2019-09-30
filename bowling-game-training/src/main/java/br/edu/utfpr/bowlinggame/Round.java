package br.edu.utfpr.bowlinggame;

public interface Round {

    Integer getScore();

    default Boolean isStrike() {
        return false;
    }

    default Boolean isSpare() {
        return false;
    }
    
    default Boolean isIncomplete() {
        return false;
    }

}