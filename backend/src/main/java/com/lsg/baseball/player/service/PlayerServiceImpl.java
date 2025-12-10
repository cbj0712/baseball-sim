package com.lsg.baseball.player.service;

import com.lsg.baseball.common.api.ErrorCode;
import com.lsg.baseball.common.exception.BusinessException;
import com.lsg.baseball.player.domain.Player;
import com.lsg.baseball.player.dto.request.PlayerCreateRequest;
import com.lsg.baseball.player.dto.response.PlayerResponse;
import com.lsg.baseball.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public PlayerResponse createPlayer(PlayerCreateRequest request) {
        Player player = playerRepository.save(toEntity(request));

        return toResponse(player);
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerResponse getPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new BusinessException(ErrorCode.PLAYER_NOT_FOUND));

        return toResponse(player);
    }

    private Player toEntity(PlayerCreateRequest request){
        Player player = new Player();

        player.setName(request.getName());
        player.setBirthDate(request.getBirthDate());
        player.setNationality(request.getNationality());
        player.setUniformNumber(request.getUniformNumber());

        player.setHeightCm(request.getHeightCm());
        player.setWeightKg(request.getWeightKg());
        player.setBodyType(request.getBodyType());

        player.setMainPosition(request.getMainPosition());
        player.setSubPositions(request.getSubPositions());
        player.setThrowHand(request.getThrowHand());
        player.setBatHand(request.getBatHand());
        player.setArmSlot(request.getArmSlot());

        player.setCondition(request.getCondition());
        player.setFatigue(request.getFatigue());
        player.setFitness(request.getFitness());

        player.setInjuryStatus(request.getInjuryStatus());
        player.setInjuryDaysLeft(request.getInjuryDaysLeft());

        player.setSatisfaction(50);
        player.setLoyalty(50);

        player.setPotential(request.getPotential());
        player.setOverall(request.getOverall());
        player.setStamina(request.getStamina());
        player.setComposure(request.getComposure());

        return player;
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
