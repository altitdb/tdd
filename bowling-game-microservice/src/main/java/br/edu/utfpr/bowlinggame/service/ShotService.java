package br.edu.utfpr.bowlinggame.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Iterables;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.MaximumRoundException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ShotService {

    @Autowired
    private RoundRepository roundRepository;
    
    public RoundsDTO shot(Integer shot) {
        long count = roundRepository.count();
        if (count == 10) {
            throw new MaximumRoundException();
        }
        List<Round> rounds = roundRepository.findAll();

        Round round = null;
        if (CollectionUtils.isEmpty(rounds)) {
            round = new Round(shot);
        } else {
            round = Iterables.getLast(rounds);
            if (round.isSpare()) {
                round.bonus(shot);
                roundRepository.save(round);
                round = new Round(shot);
            } else if (round.isIncomplete()) {
                round.secondShot(shot);
            } else {
                round = new Round(shot);
            }
        }
        
        Round savedRound = roundRepository.save(round);
        List<RoundDTO> roundsDTO = rounds.stream().map(this::toDto).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(rounds)) {
            roundsDTO.add(toDto(savedRound));
        } else {
            Round last = Iterables.getLast(rounds);
            
            if (last.isSpare()) {
                RoundDTO lastDTO = Iterables.getLast(roundsDTO);
                roundsDTO.remove(lastDTO);
                roundsDTO.add(new RoundDTO(lastDTO.getFirstShot(), lastDTO.getSecondShot(), shot, lastDTO.getScore()));
            }
            if (!last.equals(savedRound)) {
                roundsDTO.add(toDto(savedRound));
            }
        }
        return RoundsDTO.builder()
                .withRounds(roundsDTO)
                .build();
    }

    private RoundDTO toDto(Round savedRound) {
        return RoundDTO.builder()
                .withFirstShot(savedRound.getFirstShot())
                .withSecondShot(savedRound.getSecondShot())
                .withScore(savedRound.getScore())
                .build();
    }

}
