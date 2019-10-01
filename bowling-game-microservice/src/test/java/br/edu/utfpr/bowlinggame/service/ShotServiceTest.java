package br.edu.utfpr.bowlinggame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.MaximumRoundException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ShotService.class })
class ShotServiceTest {

    @Autowired
    private ShotService shotService;
    @MockBean
    private RoundRepository roundRepository;

    @Test
    void shouldSaveAShot() {
        Round roundEntity = new Round(10);
        BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
        RoundDTO round = RoundDTO.builder()
            .withFirstShot(10)
            .withScore(10)
            .build();
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(Collections.singletonList(round))
            .build();
        
        RoundsDTO rounds = shotService.shot(10);

        Assertions.assertEquals(expected, rounds);
    }
    
    @Test
    void shouldSaveTwoShots() {
        Round roundEntity1 = new Round(3);
        BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
        Round roundEntity2 = new Round(3);
        roundEntity2.secondShot(5);
        BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
        RoundDTO round = RoundDTO.builder()
            .withFirstShot(3)
            .withSecondShot(5)
            .withScore(8)
            .build();
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(Collections.singletonList(round))
            .build();
        
        RoundsDTO rounds = shotService.shot(5);

        Assertions.assertEquals(expected, rounds);
    }

    @Test
    void shouldSaveThreeShots() {
        Round roundEntity1 = new Round(3);
        roundEntity1.secondShot(5);
        BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
        Round roundEntity2 = new Round(4);
        BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
        RoundDTO round1 = RoundDTO.builder()
            .withFirstShot(3)
            .withSecondShot(5)
            .withScore(8)
            .build();
        RoundDTO round2 = RoundDTO.builder()
            .withFirstShot(4)
            .withScore(4)
            .build();
        List<RoundDTO> roundsDTO = new ArrayList<>();
        roundsDTO.add(round1);
        roundsDTO.add(round2);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(roundsDTO)
            .build();
        
        RoundsDTO rounds = shotService.shot(4);

        Assertions.assertEquals(expected, rounds);
    }
    
    @Test
    void shouldSaveNewRoundBeforeStrike() {
        Round roundEntity1 = new Round(10);
        BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
        Round roundEntity2 = new Round(3);
        BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
        RoundDTO round1 = RoundDTO.builder()
            .withFirstShot(10)
            .withSecondShot(null)
            .withScore(10)
            .build();
        RoundDTO round2 = RoundDTO.builder()
            .withFirstShot(3)
            .withScore(3)
            .build();
        List<RoundDTO> roundsDTO = new ArrayList<>();
        roundsDTO.add(round1);
        roundsDTO.add(round2);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(roundsDTO)
            .build();
        
        RoundsDTO rounds = shotService.shot(3);

        Assertions.assertEquals(expected, rounds);
    }
    
    @Test
    void shouldSaveNewRoundBeforeSpare() {
        Round roundEntity1 = new Round(5);
        roundEntity1.secondShot(5);
        BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
        Round roundEntity2 = new Round(3);
        BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
        RoundDTO round1 = RoundDTO.builder()
            .withFirstShot(5)
            .withSecondShot(5)
            .withBonus(3)
            .withScore(13)
            .build();
        RoundDTO round2 = RoundDTO.builder()
            .withFirstShot(3)
            .withScore(3)
            .build();
        List<RoundDTO> roundsDTO = new ArrayList<>();
        roundsDTO.add(round1);
        roundsDTO.add(round2);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(roundsDTO)
            .build();
        
        RoundsDTO rounds = shotService.shot(3);

        Assertions.assertEquals(expected, rounds);
    }
    
    @Test
    void shouldntAcceptMoreThanTenRounds() {
        BDDMockito.when(roundRepository.count()).thenReturn(10L);

        Assertions.assertThrows(MaximumRoundException.class, () -> shotService.shot(4));
    }
    
    @Test
    void shouldCalculateSparePoints() {
        Round roundEntity1 = new Round(5);
        roundEntity1.secondShot(5);
        BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
        Round roundEntity2 = new Round(3);
        BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
        Round roundEntity3 = new Round(5);
        roundEntity3.secondShot(5);
        roundEntity3.bonus(3);
        BDDMockito.when(roundRepository.save(roundEntity3)).thenReturn(roundEntity3);
        RoundDTO round1 = RoundDTO.builder()
            .withFirstShot(5)
            .withSecondShot(5)
            .withBonus(3)
            .withScore(13)
            .build();
        RoundDTO round2 = RoundDTO.builder()
            .withFirstShot(3)
            .withScore(3)
            .build();
        List<RoundDTO> roundsDTO = new ArrayList<>();
        roundsDTO.add(round1);
        roundsDTO.add(round2);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(roundsDTO)
            .build();
        
        RoundsDTO rounds = shotService.shot(3);

        Assertions.assertEquals(expected, rounds);
    }

}
