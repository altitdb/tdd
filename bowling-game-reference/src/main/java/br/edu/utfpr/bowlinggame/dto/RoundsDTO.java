package br.edu.utfpr.bowlinggame.dto;

import java.util.List;
import java.util.Objects;

public class RoundsDTO {

    private List<RoundDTO> rounds;

    private RoundsDTO(RoundsDTOBuilder roundsDTOBuilder) {
        this.rounds = roundsDTOBuilder.rounds;
    }

    protected RoundsDTO() {
    	//for frameworks
    }

    public List<RoundDTO> getRounds() {
        return rounds;
    }

    public static RoundsDTOBuilder builder() {
        return new RoundsDTOBuilder();
    }

    public static final class RoundsDTOBuilder {
        private List<RoundDTO> rounds;

        private RoundsDTOBuilder() {
        }

        public RoundsDTOBuilder withRounds(List<RoundDTO> rounds) {
            this.rounds = rounds;
            return this;
        }

        public RoundsDTO build() {
            return new RoundsDTO(this);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(rounds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundsDTO)) {
            return false;
        }
        RoundsDTO other = (RoundsDTO) obj;
        return Objects.equals(rounds, other.rounds);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RoundsDTO [rounds=");
        builder.append(rounds);
        builder.append("]");
        return builder.toString();
    }

}
