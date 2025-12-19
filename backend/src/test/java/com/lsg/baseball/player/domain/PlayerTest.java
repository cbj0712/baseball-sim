package com.lsg.baseball.player.domain;

import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.exception.BusinessException;
import com.lsg.baseball.player.domain.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {
    private Player player;

    private Player createPlayer() {
        return Player.builder()
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
    }

    @BeforeEach
    void setUp() {
        this.player = createPlayer();
    }

    @DisplayName("등번호 변경 시 벗어난 하한 범위 설정 예외")
    @Test
    void changeUniformNumber_outOfLowerRange_throwsBusinessException() {
        assertThatThrownBy(() -> player.changeUniformNumber(-1))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ErrorCode.INVALID_UNIFORM_NUMBER.getMessage());
    }

    @DisplayName("등번호 변경 시 벗어난 상한 범위 설정 예외")
    @Test
    void changeUniformNumber_outOfUpperRange_throwsBusinessException() {
        assertThatThrownBy(() -> player.changeUniformNumber(201))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ErrorCode.INVALID_UNIFORM_NUMBER.getMessage());
    }

    @DisplayName("구단 이적 시 팀 ID 추가 안한 경우")
    @Test
    void transferTo_invalidId_throwsBusinessException() {
        assertThatThrownBy(() -> player.transferTo(null))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ErrorCode.INVALID_TEAM_ID.getMessage());
    }

    @DisplayName("정상적인 등번호 변경")
    @Test
    void changeUniformNumber() {
        assertThatCode(() -> player.changeUniformNumber(10))
                .doesNotThrowAnyException();

        assertThat(player.getUniformNumber()).isEqualTo(10);
    }
}
