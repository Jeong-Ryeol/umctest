package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiMission1.code.MissionCompleteRequestDTO;
import umc.study.ApiMission1.code.MissionRequestDTO;
import umc.study.ApiMission1.code.MissionResponseDTO;
import umc.study.ApiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.exception.general.PageValidationException;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.service.MissionService.MissionQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/create")
    public ApiResponse<MissionResponseDTO> createMission(@RequestBody @Valid MissionRequestDTO request) {
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toResponseDTO(mission));
    }

    // 3번 API: 내가 진행중인 미션 목록 조회
    @GetMapping("/me")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "Header의 memberId 기준으로 CHALLENGING 상태인 미션만 페이징하여 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON4004", description = "페이지는 1 이상이어야 합니다.")
    })

    public ApiResponse<MissionResponseDTO.MyMissionPreviewListDTO> getMyOngoingMissions(
            @RequestHeader("memberId") Long memberId,
            @RequestParam("page") Integer page
    ) {
        if (page == null || page < 1) {
            throw new PageValidationException();
        }

        return ApiResponse.onSuccess(
                missionQueryService.getMyOngoingMissions(memberId, page - 1)
        );
    }

    @PatchMapping("/progress")
    @Operation(summary = "진행 중인 미션 완료 처리 API", description = "memberId를 헤더로, missionId를 Body로 전달하여 상태를 COMPLETED로 바꿉니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON404", description = "진행 중인 미션이 존재하지 않습니다.")
    })

    public ApiResponse<String> completeMission(
            @RequestHeader("memberId") Long memberId,
            @RequestBody @Valid MissionCompleteRequestDTO request) {

        missionCommandService.completeMission(memberId, request.getMissionId());
        return ApiResponse.onSuccess("미션 완료 처리 성공");
    }

}
