package br.edu.utfpr.bowlinggame.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.bowlinggame.entity.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {

    @Query("select coalesce(sum(r.score), 0) from Round r")
    Integer getScore();

}
