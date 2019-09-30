package br.edu.utfpr.bowlinggame.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "round")
public class Round {

    @Id
    private UUID id;
    private Integer score;

    private Round(RoundBuilder roundBuilder) {
        this.id = roundBuilder.id;
        this.score = roundBuilder.score;
    }

    protected Round() {
        // for frameworks
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

}
