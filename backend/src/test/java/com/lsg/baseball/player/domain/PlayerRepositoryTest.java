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

        player.setSubPositions(List.of(
                Position._3B,
                Position.SS
        ));

        Player saved = playerRepository.save(player);

        Player found = playerRepository.findById(saved.getId())
                .orElseThrow(() -> new IllegalArgumentException("선수를 찾을 수 없습니다"));

        assertThat(found.getSubPositions())
                .isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(Position._3B, Position.SS);
    }

    private Player createTestPlayer() {
        Player player = new Player();

        player.setName("홍길동");
        player.setBirthDate(LocalDate.of(1990, 1, 1));
        player.setNationality("대한민국");
        player.setUniformNumber(5);
        player.setHeightCm(189);
        player.setWeightKg(68);
        player.setBodyType(BodyType.AVERAGE);
        player.setMainPosition(Position._1B);
        player.setThrowHand(ThrowHand.R);
        player.setBatHand(BatHand.S);
        player.setArmSlot(ArmSlot.SIDE_ARM);
        player.setCondition(100);
        player.setFatigue(5);
        player.setFitness(100);
        player.setInjuryStatus(InjuryStatus.HEALTHY);
        player.setInjuryDaysLeft(0);
        player.setSatisfaction(100);
        player.setLoyalty(100);
        player.setPotential(100);
        player.setOverall(100);
        player.setStamina(100);
        player.setComposure(100);

        return player;
    }

}
