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
    @Min(0)
    @Max(200)
    private Integer uniformNumber;

    @NotNull
    @Min(150)
    @Max(220)
    private Integer heightCm;
    @NotNull
    @Min(40)
    @Max(180)
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
    @Min(0)
    @Max(100)
    private Integer condition;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer fatigue;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer fitness;

    @NotNull
    private InjuryStatus injuryStatus;
    @Min(0)
    @Max(1000)
    private Integer injuryDaysLeft;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer potential;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer overall;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer stamina;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer composure;
}
