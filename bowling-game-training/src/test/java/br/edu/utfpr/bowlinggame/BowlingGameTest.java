package br.edu.utfpr.bowlinggame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

    @Test
    public void shouldGetScore() {
        int expected = 0;

        BowlingGame game = new BowlingGame();
        Integer score = game.getScore();
        
        Assertions.assertSame(expected, score);
    }
    
    @Test
    public void shouldMakeOneShot() {
        int expected = 10;

        BowlingGame game = new BowlingGame();
        game.shot(10);
        Integer score = game.getScore();
        
        Assertions.assertSame(expected, score);
    }
    
    @Test
    public void shouldMakeOneShotMoreThanTen() {
        BowlingGame game = new BowlingGame();
        Assertions.assertThrows(MaximumScoreException.class, () -> game.shot(11));
    }
    
    @Test
    public void shouldMakeTwoShotMoreThanTen() {
        BowlingGame game = new BowlingGame();
        game.shot(6);
        Assertions.assertThrows(MaximumScoreException.class, () -> game.shot(7));
    }
    
    @Test
    public void shouldMakeTwoShotEqualTen() {
        BowlingGame game = new BowlingGame();
        game.shot(6);
        game.shot(4);
        Assertions.assertSame(10, game.getScore());
    }
    
    @Test
    public void shouldMakeShotTenRounds() {
        BowlingGame game = new BowlingGame();
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        game.shot(6);
        game.shot(3);
        Assertions.assertSame(90, game.getScore());
    }
    
    @Test
    public void shouldCalculateBonusStrike() {
        BowlingGame game = new BowlingGame();
        game.shot(10);
        game.shot(3);
        game.shot(6);
        Assertions.assertSame(28, game.getScore());
    }
    
    @Test
    public void shouldCalculateBonusSpare() {
        BowlingGame game = new BowlingGame();
        game.shot(5);
        game.shot(5);
        game.shot(3);
        Assertions.assertSame(16, game.getScore());
    }
    
    @Test
    public void shouldGivenTwoWhenStrikeInTenRound() {
        BowlingGame game = new BowlingGame();
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(10);
        game.shot(3);
        game.shot(3);
        Assertions.assertEquals((Integer) 272, game.getScore());
    }
}
