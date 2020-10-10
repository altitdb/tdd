package br.edu.utfpr.bowlinggame.dto;

import java.util.Objects;

public class RoundDTO {

    private Integer firstShot;
    private Integer secondShot;
    private Integer bonus;
    private Integer score;

    private RoundDTO(RoundDTOBuilder roundDTOBuilder) {
        this.firstShot = roundDTOBuilder.firstShot;
        this.secondShot = roundDTOBuilder.secondShot;
        this.bonus = roundDTOBuilder.bonus;
        this.score = roundDTOBuilder.score;
    }

    public RoundDTO() {
    }

    public RoundDTO(Integer firstShot, Integer secondShot, Integer score) {
        this.firstShot = firstShot;
        this.secondShot = secondShot;
        this.score = score;
    }
    
    public RoundDTO(Integer firstShot, Integer secondShot, Integer bonus, Integer score) {
        this.firstShot = firstShot;
        this.secondShot = secondShot;
        this.bonus = bonus;
        this.score = score;
    }

    public Integer getFirstShot() {
        return firstShot;
    }

    public Integer getSecondShot() {
        return secondShot;
    }

    public Integer getScore() {
        return score;
    }

    public static RoundDTOBuilder builder() {
        return new RoundDTOBuilder();
    }

    public static final class RoundDTOBuilder {
        private Integer firstShot;
        private Integer secondShot;
        private Integer bonus;
        private Integer score;

        private RoundDTOBuilder() {
        }

        public RoundDTOBuilder withFirstShot(Integer firstShot) {
            this.firstShot = firstShot;
            return this;
        }

        public RoundDTOBuilder withSecondShot(Integer secondShot) {
            this.secondShot = secondShot;
            return this;
        }

        public RoundDTOBuilder withBonus(Integer bonus) {
            this.bonus = bonus;
            return this;
        }

        public RoundDTOBuilder withScore(Integer score) {
            this.score = score;
            return this;
        }

        public RoundDTO build() {
            return new RoundDTO(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstShot, score, secondShot);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundDTO)) {
            return false;
        }
        RoundDTO other = (RoundDTO) obj;
        return Objects.equals(firstShot, other.firstShot) && Objects.equals(score, other.score)
                && Objects.equals(bonus, other.bonus) && Objects.equals(secondShot, other.secondShot);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RoundDTO [firstShot=");
        builder.append(firstShot);
        builder.append(", secondShot=");
        builder.append(secondShot);
        builder.append(", bonus=");
        builder.append(bonus);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }

}
