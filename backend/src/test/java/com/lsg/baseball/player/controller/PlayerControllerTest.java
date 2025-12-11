package com.lsg.baseball.player.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsg.baseball.player.domain.enums.*;
import com.lsg.baseball.player.dto.request.PlayerCreateRequest;
import com.lsg.baseball.player.dto.response.PlayerResponse;
import com.lsg.baseball.player.service.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PlayerService playerService;
    
    @DisplayName("선수 생성 시 PlayerResponse 반환")
    @Test
    @WithMockUser(username = "test-user")
    void createPlayer_success() throws Exception {
        PlayerCreateRequest request = new PlayerCreateRequest();

        request.setName("홍길동");
        request.setBirthDate(LocalDate.of(1995, 1, 1));
        request.setNationality("대한민국");
        request.setUniformNumber(10);
        request.setHeightCm(180);
        request.setWeightKg(80);
        request.setBodyType(BodyType.MUSCULAR);
        request.setMainPosition(Position.SS);
        request.setThrowHand(ThrowHand.R);
        request.setBatHand(BatHand.R);
        request.setArmSlot(ArmSlot.OVER_HAND);
        request.setCondition(100);
        request.setFatigue(0);
        request.setFitness(100);
        request.setInjuryStatus(InjuryStatus.HEALTHY);
        request.setInjuryDaysLeft(0);
        request.setPotential(80);
        request.setOverall(75);
        request.setStamina(80);
        request.setComposure(70);

        PlayerResponse response = PlayerResponse.builder()
                .id(1L)
                .name("홍길동")
                .birthDate(request.getBirthDate())
                .nationality(request.getNationality())
                .uniformNumber(request.getUniformNumber())
                .heightCm(request.getHeightCm())
                .weightKg(request.getWeightKg())
                .bodyType(request.getBodyType())
                .mainPosition(request.getMainPosition())
                .subPositions(request.getSubPositions())
                .throwHand(request.getThrowHand())
                .batHand(request.getBatHand())
                .armSlot(request.getArmSlot())
                .condition(request.getCondition())
                .fatigue(request.getFatigue())
                .fitness(request.getFitness())
                .injuryStatus(request.getInjuryStatus())
                .injuryDaysLeft(request.getInjuryDaysLeft())
                .satisfaction(50)
                .loyalty(50)
                .potential(request.getPotential())
                .overall(request.getOverall())
                .stamina(request.getStamina())
                .composure(request.getComposure())
                .build();

        given(playerService.createPlayer(any(PlayerCreateRequest.class)))
                .willReturn(response);

        // when & then
        mockMvc.perform(
                post("/api/players")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated()) // or isOk()
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("홍길동"));
    }
}
