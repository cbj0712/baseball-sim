package com.lsg.baseball.player.repository;

import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByMainPosition(Position mainPosition);
    List<Player> findByNameContaining(String name);
}
