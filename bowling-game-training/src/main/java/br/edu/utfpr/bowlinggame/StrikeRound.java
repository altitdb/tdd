package br.edu.utfpr.bowlinggame;

public class StrikeRound implements Round {

    private Integer score = 0;

    public StrikeRound() {
        this.score = 10;
    }
    
    public StrikeRound(Integer points) {
        this.score = 10 + points;
    }
    
    public StrikeRound(Integer score, Integer shot) {
        this.score = score + shot;
    }

    @Override
    public Integer getScore() {
        return score;
    }

    @Override
    public Boolean isStrike() {
        return true;
    }

}