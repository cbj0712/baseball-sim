package com.lsg.baseball.player.dto.request;

import com.lsg.baseball.player.domain.enums.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PlayerCreateRequest {
    @NotBlank
    private String name;
    @NotNull
    @PastOrPresent
    private LocalDate birthDate;
    @NotBlank
    private String nationality;
    @PositiveOrZero
    @Max(200)
    private Integer uniformNumber;

    @NotNull
    @PositiveOrZero
    private Integer heightCm;
    @NotNull
    @PositiveOrZero
    private Integer weightKg;
    @NotNull
    private BodyType bodyType;

    @NotNull
    private Position mainPosition;
    private List<Position> subPositions;

    @NotNull
    private ThrowHand throwHand;
    @NotNull
    private BatHand batHand;
    @NotNull
    private ArmSlot armSlot;

    @NotNull
    @PositiveOrZero
    private Integer condition;
    @NotNull
    @PositiveOrZero
    private Integer fatigue;
    @NotNull
    @PositiveOrZero
    private Integer fitness;

    @NotNull
    private InjuryStatus injuryStatus;
    @PositiveOrZero
    private Integer injuryDaysLeft;

    @NotNull
    @PositiveOrZero
    private Integer potential;
    @NotNull
    @PositiveOrZero
    private Integer overall;
    @NotNull
    @PositiveOrZero
    private Integer stamina;
    @NotNull
    @PositiveOrZero
    private Integer composure;
}
