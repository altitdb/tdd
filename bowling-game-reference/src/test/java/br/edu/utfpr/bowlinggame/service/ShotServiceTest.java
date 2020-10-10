package br.edu.utfpr.bowlinggame.service;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.InvalidShotException;
import br.edu.utfpr.bowlinggame.exception.MaximumRoundException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ShotService.class })
class ShotServiceTest {

    @Autowired
    private ShotService shotService;
    @MockBean
    private RoundRepository roundRepository;
    
    @Test
    void shouldReturnInvalidShotForNegativeShot() {
        Assertions.assertThrows(InvalidShotException.class, () -> shotService.shot(-1));
    }
    
    @Test
    void shouldReturnInvalidShotForOutlierShot() {
		Assertions.assertThrows(InvalidShotException.class, () -> shotService.shot(11));
	}

	@Test
	void shouldReturnInvalidSecondShotForNegativeShot() {
		Round roundEntity = new Round(0);
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		shotService.shot(0);
		Assertions.assertThrows(InvalidShotException.class, () -> shotService.shot(-1));
	}

	@Test
	void shouldReturnInvalidSecondShotForOutlierShot() {
		Round roundEntity = new Round(0);
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		shotService.shot(0);
		Assertions.assertThrows(InvalidShotException.class, () -> shotService.shot(11));
	}

	@Test
	void shouldReturnScoreTenAndRoundOne() {
		Round roundEntity = new Round(0);
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		shotService.shot(0);
		
		Round roundEntity2 = new Round(10);
		BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
		
		BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity));
		RoundsDTO actual = shotService.shot(10);

		RoundDTO round = RoundDTO.builder().withFirstShot(0).withSecondShot(10).withScore(10).build();
		RoundsDTO expected = RoundsDTO.builder().withRounds(Collections.singletonList(round)).build();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void shouldReturnScoreZeroAndRoundTen() {
		Round roundEntity = new Round(0);
		roundEntity.secondShot(0);
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		
		Round roundEntitySimple = new Round(0);
		BDDMockito.when(roundRepository.findAll()).thenReturn(Arrays.asList(roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntitySimple));
		
		RoundsDTO actual = shotService.shot(0);

		RoundDTO round = RoundDTO.builder().withFirstShot(0).withSecondShot(0).withScore(0).build();
		RoundsDTO expected = RoundsDTO.builder()
				.withRounds(Arrays.asList(round, round, round, round, round, round, round, round, round, round))
				.build();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void shouldReturnScoreOneHundredAndRoundTen() {
		Round roundEntity = new Round(10);
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		
		BDDMockito.when(roundRepository.findAll()).thenReturn(Arrays.asList(roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity, roundEntity));
		
		RoundsDTO actual = shotService.shot(10);

		RoundDTO round1 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round2 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round3 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round4 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round5 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round6 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round7 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round8 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round9 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundDTO round10 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
		RoundsDTO expected = RoundsDTO.builder()
				.withRounds(Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10))
				.build();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void shouldReturnInvalidRound() {
		BDDMockito.when(roundRepository.count()).thenReturn(10L);
		
		Assertions.assertThrows(MaximumRoundException.class, () -> shotService.shot(10));
	}

//	@Test
//	void shouldSaveAShot() {
//		Round roundEntity = new Round(10);
//		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
//		RoundDTO round = RoundDTO.builder().withFirstShot(10).withScore(10).build();
//		RoundsDTO expected = RoundsDTO.builder().withRounds(Collections.singletonList(round)).build();
//
//		RoundsDTO rounds = shotService.shot(10);
//
//		Assertions.assertEquals(expected, rounds);
//	}
//
//	@Test
//	void shouldSaveTwoShots() {
//		Round roundEntity1 = new Round(3);
//		BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
//		Round roundEntity2 = new Round(3);
//		roundEntity2.secondShot(5);
//		BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
//		RoundDTO round = RoundDTO.builder().withFirstShot(3).withSecondShot(5).withScore(8).build();
//		RoundsDTO expected = RoundsDTO.builder().withRounds(Collections.singletonList(round)).build();
//
//		RoundsDTO rounds = shotService.shot(5);
//
//		Assertions.assertEquals(expected, rounds);
//	}
//
//	@Test
//	void shouldSaveThreeShots() {
//		Round roundEntity1 = new Round(3);
//		roundEntity1.secondShot(5);
//		BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
//		Round roundEntity2 = new Round(4);
//		BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
//		RoundDTO round1 = RoundDTO.builder().withFirstShot(3).withSecondShot(5).withScore(8).build();
//		RoundDTO round2 = RoundDTO.builder().withFirstShot(4).withScore(4).build();
//		List<RoundDTO> roundsDTO = new ArrayList<>();
//		roundsDTO.add(round1);
//		roundsDTO.add(round2);
//		RoundsDTO expected = RoundsDTO.builder().withRounds(roundsDTO).build();
//
//		RoundsDTO rounds = shotService.shot(4);
//
//		Assertions.assertEquals(expected, rounds);
//	}
//
//	@Test
//	void shouldSaveNewRoundBeforeStrike() {
//		Round roundEntity1 = new Round(10);
//		BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
//		Round roundEntity2 = new Round(3);
//		BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
//		RoundDTO round1 = RoundDTO.builder().withFirstShot(10).withSecondShot(null).withScore(10).build();
//		RoundDTO round2 = RoundDTO.builder().withFirstShot(3).withScore(3).build();
//		List<RoundDTO> roundsDTO = new ArrayList<>();
//		roundsDTO.add(round1);
//		roundsDTO.add(round2);
//		RoundsDTO expected = RoundsDTO.builder().withRounds(roundsDTO).build();
//
//		RoundsDTO rounds = shotService.shot(3);
//
//		Assertions.assertEquals(expected, rounds);
//	}
//
//	@Test
//	void shouldSaveNewRoundBeforeSpare() {
//		Round roundEntity1 = new Round(5);
//		roundEntity1.secondShot(5);
//		BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
//		Round roundEntity2 = new Round(3);
//		BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
//		RoundDTO round1 = RoundDTO.builder().withFirstShot(5).withSecondShot(5).withBonus(3).withScore(13).build();
//		RoundDTO round2 = RoundDTO.builder().withFirstShot(3).withScore(3).build();
//		List<RoundDTO> roundsDTO = new ArrayList<>();
//		roundsDTO.add(round1);
//		roundsDTO.add(round2);
//		RoundsDTO expected = RoundsDTO.builder().withRounds(roundsDTO).build();
//
//		RoundsDTO rounds = shotService.shot(3);
//
//		Assertions.assertEquals(expected, rounds);
//	}
//
//	@Test
//	void shouldntAcceptMoreThanTenRounds() {
//		BDDMockito.when(roundRepository.count()).thenReturn(10L);
//
//		Assertions.assertThrows(MaximumRoundException.class, () -> shotService.shot(4));
//	}
//
//	@Test
//	void shouldCalculateSparePoints() {
//		Round roundEntity1 = new Round(5);
//		roundEntity1.secondShot(5);
//		BDDMockito.when(roundRepository.findAll()).thenReturn(Collections.singletonList(roundEntity1));
//		Round roundEntity2 = new Round(3);
//		BDDMockito.when(roundRepository.save(roundEntity2)).thenReturn(roundEntity2);
//		Round roundEntity3 = new Round(5);
//		roundEntity3.secondShot(5);
//		roundEntity3.bonus(3);
//		BDDMockito.when(roundRepository.save(roundEntity3)).thenReturn(roundEntity3);
//		RoundDTO round1 = RoundDTO.builder().withFirstShot(5).withSecondShot(5).withBonus(3).withScore(13).build();
//		RoundDTO round2 = RoundDTO.builder().withFirstShot(3).withScore(3).build();
//		List<RoundDTO> roundsDTO = new ArrayList<>();
//		roundsDTO.add(round1);
//		roundsDTO.add(round2);
//		RoundsDTO expected = RoundsDTO.builder().withRounds(roundsDTO).build();
//
//		RoundsDTO rounds = shotService.shot(3);
//
//		Assertions.assertEquals(expected, rounds);
//	}

}
