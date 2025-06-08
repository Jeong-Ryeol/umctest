package umc.study.service.MissionService;

import umc.study.ApiMission1.code.MissionRequestDTO;
import umc.study.domain.Mission;

public interface MissionCommandService {
    Mission createMission(MissionRequestDTO request);

    void completeMission(Long memberId, Long missionId);

}