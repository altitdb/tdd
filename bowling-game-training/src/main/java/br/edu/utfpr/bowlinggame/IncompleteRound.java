package br.edu.utfpr.bowlinggame;

public class IncompleteRound implements Round {

    private Integer firstShot;
    private Integer secondShot;
    
    public IncompleteRound(Integer firstShot, Integer secondShot) {
        this.firstShot = firstShot;
        this.secondShot = secondShot;
    }
    
    public IncompleteRound(Integer firstShot) {
        this.firstShot = firstShot;
        this.secondShot = 0;
    }
    
    @Override
    public Boolean isIncomplete() {
        return true;
    }

    @Override
    public Integer getScore() {
        return firstShot + secondShot;
    }

}
