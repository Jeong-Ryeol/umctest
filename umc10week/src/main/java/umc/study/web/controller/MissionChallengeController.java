package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.ApiMission1.code.MissionChallengeRequestDTO;
import umc.study.ApiPayload.ApiResponse;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.ChallengeService.MissionChallengeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission-challenges")
public class MissionChallengeController {

    private final MissionChallengeService missionChallengeService;

    @PostMapping("/create")
    public ApiResponse<String> challengeMission(@RequestBody @Valid MissionChallengeRequestDTO request) {
        MemberMission memberMission = missionChallengeService.challengeMission(request);
        return ApiResponse.onSuccess("도전 완료! id: " + memberMission.getId());
    }
}