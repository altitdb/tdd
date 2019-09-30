package br.edu.utfpr.bowlinggame;

public class RoundFactory {

    public static Round factory(Integer shot) {
        if (shot == 10) {
            return new StrikeRound();
        }

        return new IncompleteRound(shot);
    }

    public static Round factory(IncompleteRound round, Integer shot) {
        Integer score = round.getScore() + shot;
        if (score == 10) {
            return new SpareRound(round.getScore(), shot);
        }
        return new NormalRound(round.getScore(), shot);
    }

    public static Round factory(SpareRound round, Integer shot) {
        return new SpareRound(round, shot);
    }

    public static Round factory(Round last) {
        return new StrikeRound(last.getScore());
    }

    public static Round factory(Round penult, Round last) {
        return new StrikeRound(penult.getScore() + last.getScore());
    }

    public static Round factory(StrikeRound last, Integer shot) {
        return new StrikeRound(last.getScore(), shot);
    }

}
