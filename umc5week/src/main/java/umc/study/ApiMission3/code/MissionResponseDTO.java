package umc.study.ApiMission3.code;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionResponseDTO {
    private Long missionId;
    private String missionSpec;
    private Integer reward;
    private String storeName;
}