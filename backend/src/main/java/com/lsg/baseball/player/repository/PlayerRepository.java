package com.lsg.baseball.player.repository;

import com.lsg.baseball.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
