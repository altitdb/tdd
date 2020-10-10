package br.edu.utfpr.bowlinggame.dto;

import java.util.Objects;

public class ScoreDTO {

    private Integer score;

    public ScoreDTO(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScoreDTO)) {
            return false;
        }
        ScoreDTO other = (ScoreDTO) obj;
        return Objects.equals(score, other.score);
    }

}
