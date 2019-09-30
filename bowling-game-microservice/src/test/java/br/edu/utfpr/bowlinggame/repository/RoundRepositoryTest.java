package br.edu.utfpr.bowlinggame.repository;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.edu.utfpr.bowlinggame.entity.Round;

@Profile("test")
@SpringBootTest(classes = { TestConfiguration.class })
class RoundRepositoryTest {

    @Autowired
    private RoundRepository roundRepository;

    @Test
    void shouldSumTheScore() {
        Round round = Round.builder()
                .withId(UUID.randomUUID())
                .withScore(13)
                .build();
        roundRepository.save(round);
        Integer expected = 13;

        Integer score = roundRepository.getScore();
        
        Assertions.assertSame(expected, score);
    }

}
