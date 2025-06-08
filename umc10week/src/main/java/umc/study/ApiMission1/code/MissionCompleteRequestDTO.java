package umc.study.ApiMission1.code;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MissionCompleteRequestDTO {

    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;
}
