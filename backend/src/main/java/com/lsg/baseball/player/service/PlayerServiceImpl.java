package com.lsg.baseball.player.service;

import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.exception.BusinessException;
import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.domain.PlayerFactory;
import com.lsg.baseball.player.domain.command.PlayerCreateCommand;
import com.lsg.baseball.player.dto.request.PlayerCreateRequest;
import com.lsg.baseball.player.dto.response.PlayerResponse;
import com.lsg.baseball.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public PlayerResponse createPlayer(PlayerCreateRequest request) {
        PlayerCreateCommand command = PlayerCreateCommand.from(request);

        Player player = PlayerFactory.create(command);

        if (command.teamId() != null && command.uniformNumber() != null) {
            if (playerRepository.existsByTeamIdAndUniformNumber(command.teamId(), command.uniformNumber())) {
                throw new BusinessException(ErrorCode.DUPLICATE_UNIFORM_NUMBER_IN_TEAM);
            }
        }


        Player saved = playerRepository.save(player);

        return toResponse(saved);
    }

    @Override
    public PlayerResponse getPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new BusinessException(ErrorCode.PLAYER_NOT_FOUND));

        return toResponse(player);
    }

    private PlayerResponse toResponse(Player player){
        return PlayerResponse.builder()
                .id(player.getId())
                .name(player.getName())
                .birthDate(player.getBirthDate())
                .nationality(player.getNationality())
                .uniformNumber(player.getUniformNumber())
                .heightCm(player.getHeightCm())
                .weightKg(player.getWeightKg())
                .bodyType(player.getBodyType())
                .mainPosition(player.getMainPosition())
                .subPositions(player.getSubPositions())
                .throwHand(player.getThrowHand())
                .batHand(player.getBatHand())
                .armSlot(player.getArmSlot())
                .condition(player.getCondition())
                .fatigue(player.getFatigue())
                .fitness(player.getFitness())
                .injuryStatus(player.getInjuryStatus())
                .injuryDaysLeft(player.getInjuryDaysLeft())
                .satisfaction(player.getSatisfaction())
                .loyalty(player.getLoyalty())
                .overall(player.getOverall())
                .potential(player.getPotential())
                .stamina(player.getStamina())
                .composure(player.getComposure())
                .build();
    }
}
