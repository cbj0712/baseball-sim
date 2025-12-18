package com.lsg.baseball.player.service;

import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.exception.BusinessException;
import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.PlayerFactory;
import com.lsg.baseball.player.domain.command.PlayerCreateCommand;
import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.dto.request.PlayerCreateRequest;
import com.lsg.baseball.player.dto.response.PlayerResponse;
import com.lsg.baseball.player.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private PlayerCreateRequest createRequest;

    @BeforeEach
    void setUp() {
        createRequest = new PlayerCreateRequest();

        createRequest.setName("홍길동");
        createRequest.setBirthDate(LocalDate.of(1995, 1, 1));
        createRequest.setNationality("대한민국");
        createRequest.setUniformNumber(10);
        createRequest.setHeightCm(180);
        createRequest.setWeightKg(80);
        createRequest.setBodyType(BodyType.MUSCULAR);
        createRequest.setMainPosition(Position.SS);
        createRequest.setThrowHand(ThrowHand.R);
        createRequest.setBatHand(BatHand.R);
        createRequest.setArmSlot(ArmSlot.OVER_HAND);
        createRequest.setCondition(100);
        createRequest.setFatigue(0);
        createRequest.setFitness(100);
        createRequest.setInjuryStatus(InjuryStatus.HEALTHY);
        createRequest.setInjuryDaysLeft(0);
        createRequest.setPotential(80);
        createRequest.setOverall(75);
        createRequest.setStamina(80);
        createRequest.setComposure(70);
        createRequest.setTeamId(1L);
    }

    @DisplayName("createPlayer - Player를 생성/저장하고 응답을 반환")
    @Test
    void createPlayer_success() {
        given(playerRepository.save(any(Player.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        given(playerRepository.existsByTeamIdAndUniformNumber(1L, 10)).willReturn(false);

        PlayerResponse response = playerService.createPlayer(createRequest);

        ArgumentCaptor<Player> captor = ArgumentCaptor.forClass(Player.class);
        then(playerRepository).should().save(captor.capture());
        then(playerRepository).should().existsByTeamIdAndUniformNumber(1L, 10);

        Player saved = captor.getValue();

        assertThat(saved.getName()).isEqualTo(createRequest.getName());
        assertThat(saved.getMainPosition()).isEqualTo(createRequest.getMainPosition());

        assertThat(response.getName()).isEqualTo(createRequest.getName());
        assertThat(response.getMainPosition()).isEqualTo(createRequest.getMainPosition());
    }

    @DisplayName("createPlayer - 중복 등번호 부여 시 BusinessException 발생")
    @Test
    void createPlayer_exception() {
        createRequest.setTeamId(1L);
        createRequest.setUniformNumber(10);

        given(playerRepository.existsByTeamIdAndUniformNumber(1L, 10)).willReturn(true);

        assertThatThrownBy(() -> playerService.createPlayer(createRequest))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ErrorCode.DUPLICATE_UNIFORM_NUMBER_IN_TEAM.getMessage());

        then(playerRepository).should(never()).save(any(Player.class));
    }

    @DisplayName("createPlayer - FA인 경우 중복 체크 스킵")
    @Test
    void createPlayer_null_teamId() {
        createRequest.setTeamId(null);
        createRequest.setUniformNumber(10);

        given(playerRepository.save(any(Player.class))).willAnswer(inv -> inv.getArgument(0));

        playerService.createPlayer(createRequest);

        then(playerRepository).should(never()).existsByTeamIdAndUniformNumber(any(), any());
        then(playerRepository).should().save(any(Player.class));
    }

    @DisplayName("createPlayer - uniformNumber가 null인 경우 중복 체크 스킵")
    @Test
    void createPlayer_null_uniformNumber() {
        createRequest.setTeamId(1L);
        createRequest.setUniformNumber(null);

        given(playerRepository.save(any(Player.class))).willAnswer(inv -> inv.getArgument(0));

        playerService.createPlayer(createRequest);

        then(playerRepository).should(never()).existsByTeamIdAndUniformNumber(any(), any());
        then(playerRepository).should().save(any(Player.class));
    }

    @DisplayName("getPlayer - 존재하는 선수 정보 반환")
    @Test
    void getPlayer_success() {
        Player player = PlayerFactory.create(PlayerCreateCommand.from(createRequest));
        ReflectionTestUtils.setField(player, "id", 1L);

        given(playerRepository.findById(1L)).willReturn(Optional.of(player));

        PlayerResponse response = playerService.getPlayer(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("홍길동");
    }

    @DisplayName("getPlayer - 존재하지 않는 선수 조회 시 BusinessException 발생")
    @Test
    void getPlayer_notFound() {
        given(playerRepository.findById(1L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.getPlayer(1L))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(ErrorCode.PLAYER_NOT_FOUND.getMessage());
    }
}
