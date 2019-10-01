package br.edu.utfpr.bowlinggame.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer firstShot;
    private Integer secondShot;
    private Integer score;

    public Round(Integer firstShot) {
        this.firstShot = firstShot;
        this.score = firstShot;
    }

    protected Round() {
        // for frameworks
    }

    public Round(RoundBuilder roundBuilder) {
        this.id = roundBuilder.id;
        this.score = roundBuilder.score;
    }

    public UUID getId() {
        return id;
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

    public static RoundBuilder builder() {
        return new RoundBuilder();
    }

    public static final class RoundBuilder {
        private UUID id;
        private Integer score;

        private RoundBuilder() {
        }

        public RoundBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public RoundBuilder withScore(Integer score) {
            this.score = score;
            return this;
        }

        public Round build() {
            return new Round(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstShot, id, score, secondShot);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Round)) {
            return false;
        }
        Round other = (Round) obj;
        return Objects.equals(firstShot, other.firstShot) && Objects.equals(id, other.id)
                && Objects.equals(score, other.score) && Objects.equals(secondShot, other.secondShot);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Round [id=");
        builder.append(id);
        builder.append(", firstShot=");
        builder.append(firstShot);
        builder.append(", secondShot=");
        builder.append(secondShot);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }

    public void secondShot(Integer secondShot) {
        this.secondShot = secondShot;
        this.score = this.firstShot + this.secondShot;
    }
    
    public Boolean isStrike() {
        return firstShot == 10;
    }

    public Boolean isIncomplete() {
        return !isStrike() && firstShot != null && secondShot == null;
    }

}
