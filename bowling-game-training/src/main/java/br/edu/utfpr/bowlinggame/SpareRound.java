package br.edu.utfpr.bowlinggame;

public class SpareRound implements Round {

    private Integer shotOne;
    private Integer shotTwo;
    private Integer bonus = 0;

    public SpareRound(Integer shotOne, Integer shotTwo) {
        if (shotOne + shotTwo > 10) {
            throw new MaximumScoreException();
        }
        this.shotOne = shotOne;
        this.shotTwo = shotTwo;
    }
    
    public SpareRound(SpareRound spare, Integer bonus) {
        this.shotOne = spare.shotOne;
        this.shotTwo = spare.shotTwo;
        this.bonus = bonus;
    }
    
    @Override
    public Integer getScore() {
        return shotOne + shotTwo + bonus;
    }

    @Override
    public Boolean isSpare() {
        return true;
    }

}