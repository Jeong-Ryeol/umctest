package umc.study.ApiMission1.code;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionChallengeResponseDTO {
    private Long missionId;
    private String missionSpec;
    private String memberName;
}