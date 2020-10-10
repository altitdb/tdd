package br.edu.utfpr.bowlinggame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ScoreService {

    @Autowired
    private RoundRepository roundRepository;
    
    public ScoreDTO getScore() {
        Integer score = roundRepository.getScore();
        return new ScoreDTO(score);
    }

}
