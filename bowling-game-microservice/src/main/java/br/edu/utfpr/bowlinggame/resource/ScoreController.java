package br.edu.utfpr.bowlinggame.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.service.ScoreService;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/api/v1/score")
    public ResponseEntity<ScoreDTO> getScore() {
        ScoreDTO score = scoreService.getScore();
        return ResponseEntity.ok(score);
    }
    
}
