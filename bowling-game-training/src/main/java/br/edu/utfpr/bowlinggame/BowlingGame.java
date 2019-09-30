package br.edu.utfpr.bowlinggame;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;

public class BowlingGame implements Round {

    private List<Round> rounds = new ArrayList<>();
    private Integer score = 0;

    public Integer getScore() {
        return score + rounds.stream().mapToInt(Round::getScore).sum();
    }

    public void shot(Integer shot) {
        validateMaximumScoreShot(shot);
        
        if (rounds.isEmpty()) {
            startRound(shot);
        } else if (rounds.size() == 10) {
            lastRound(shot);
        } else {
            inProgressRounds(shot);
        }
        
        calculateStrike();
    }

    private void startRound(Integer shot) {
        Round round = RoundFactory.factory(shot);
        rounds.add(round);
    }

    private void inProgressRounds(Integer shot) {
        Round last = Iterables.getLast(rounds);
        if (last.isIncomplete()) {
            Round round = RoundFactory.factory((IncompleteRound) last, shot);
            rounds.remove(last);
            rounds.add(round);
        } else if (last.isSpare()) {
            Round spareRound = RoundFactory.factory((SpareRound) last, shot);
            rounds.remove(last);
            rounds.add(spareRound);
            startRound(shot);
        } else {
            startRound(shot);
        }
    }

    private void lastRound(Integer shot) {
        Round last = Iterables.getLast(rounds);
        if (last.isSpare()) {
            Round round = RoundFactory.factory((SpareRound) last, shot);
            rounds.remove(last);
            rounds.add(round);
        } else if (last.isStrike()) {
            Round round = RoundFactory.factory((StrikeRound) last, shot);
            rounds.remove(last);
            rounds.add(round);
        } else {
            Round round = RoundFactory.factory((IncompleteRound) last, shot);
            rounds.remove(last);
            rounds.add(round);
        }
    }

    private void calculateStrike() {
        int size = rounds.size();
        if (size > 2) {
            Round last = Iterables.getLast(rounds);
            Round penult = rounds.get(size - 2);
            Round antepenult = rounds.get(size - 3);
            if (antepenult.isStrike() && penult.isSpare()) {
                Round strike = RoundFactory.factory(penult);
                rounds.set(size - 3, strike);
            } else if (antepenult.isStrike() && penult.isStrike()) {
                Round strike = RoundFactory.factory(penult, last);
                rounds.set(size - 3, strike);
            }
        } else if (size > 1) {
            Round last = Iterables.getLast(rounds);
            Round penult = rounds.get(size - 2);
            if (penult.isStrike()) {
                Round strike = RoundFactory.factory(last);
                rounds.set(size - 2, strike);
            }
        }
    }

    private void validateMaximumScoreShot(Integer shot) {
        if (shot > 10) {
            throw new MaximumScoreException();
        }
    }
}
