package com.lsg.baseball.team.repository;

import com.lsg.baseball.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
