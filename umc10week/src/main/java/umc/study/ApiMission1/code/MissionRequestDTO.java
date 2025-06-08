package umc.study.ApiMission1.code;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRequestDTO {
    @NotNull
    private String missionSpec;

    @NotNull
    private Integer reward;

    @NotNull
    private LocalDate deadline;

    @NotNull
    private Long storeId;

}
