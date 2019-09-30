package br.edu.utfpr.bowlinggame;

public class NormalRound implements Round {

    private final Integer shotOne;
    private final Integer shotTwo;

    public NormalRound(Integer shotOne, Integer shotTwo) {
        if (shotOne + shotTwo > 10) {
            throw new MaximumScoreException();
        }
        this.shotOne = shotOne;
        this.shotTwo = shotTwo;
    }

    @Override
    public Integer getScore() {
        return shotOne + shotTwo;
    }

}