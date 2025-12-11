package com.lsg.baseball.player.domain;

import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.sql.init.mode=never")
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void saveAndSearchSubPositions() {
        Player player = createTestPlayer();

        Player saved = playerRepository.save(player);

        Player found = playerRepository.findById(saved.getId())
                .orElseThrow(() -> new IllegalArgumentException("선수를 찾을 수 없습니다"));

        assertThat(found.getSubPositions())
                .isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(Position._3B, Position.SS);
    }

    private Player createTestPlayer() {
        Player player = Player.builder()
            .name("홍길동")
            .birthDate(LocalDate.of(1990, 1, 1))
            .nationality("대한민국")
            .heightCm(189)
            .weightKg(68)
            .bodyType(BodyType.AVERAGE)
            .mainPosition(Position._1B)
            .throwHand(ThrowHand.R)
            .batHand(BatHand.S)
            .armSlot(ArmSlot.SIDE_ARM)
            .build();

        player.changeSubPositions(List.of(
                Position._3B,
                Position.SS
        ));

        return player;
    }

}
