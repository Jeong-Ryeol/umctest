package umc.study.ApiMission4.code;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.AlreadyChallenging;

@Getter
public class MissionChallengeRequestDTO {
    @NotNull
    private Long missionId;

    @NotNull
    private Long memberId;
}