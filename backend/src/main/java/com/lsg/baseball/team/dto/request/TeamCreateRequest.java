package com.lsg.baseball.team.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamCreateRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 5)
    private String code;

    @Size(max = 1000)
    private String logoUrl;

    @Size(max = 20)
    private String primaryColor;

    @Size(max = 50)
    private String city;

    private Long homeStadiumId;
}
