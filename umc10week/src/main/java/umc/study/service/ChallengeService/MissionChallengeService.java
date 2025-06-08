package umc.study.service.ChallengeService;


import umc.study.ApiMission1.code.MissionChallengeRequestDTO;
import umc.study.domain.mapping.MemberMission;

public interface MissionChallengeService {
    MemberMission challengeMission(MissionChallengeRequestDTO request);
}