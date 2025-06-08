package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiMission1.code.StoreRequestDTO;
import umc.study.ApiMission1.code.StoreResponseDTO;
import umc.study.ApiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.exception.general.PageValidationException;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.ExistStore;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/create")
    public ApiResponse<StoreResponseDTO> createStore(@RequestBody @Valid StoreRequestDTO request) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toStoreResponseDTO(store));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) {

        // ✅ page 유효성 직접 검증
        if (page == null || page < 1) {
            throw new PageValidationException(); // 우리가 만든 예외 클래스
        }

        // ✅ page-1 처리
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page - 1);

        StoreResponseDTO.ReviewPreViewListDTO result =
                StoreConverter.toReviewPreViewListDTO(reviewList);

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들을 페이징으로 조회합니다. page는 1부터 시작.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON4004", description = "페이지는 1 이상이어야 합니다.")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID (Path Variable)"),
            @Parameter(name = "page", description = "1 이상의 페이지 번호 (Query String)")
    })
    public ApiResponse<StoreResponseDTO.MissionPreviewListDTO> getMissionsByStore(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) {

        if (page == null || page < 1) {
            throw new PageValidationException();
        }

        Page<Mission> missions = storeQueryService.getMissionListByStore(storeId, page - 1);

// 변환해서 DTO로 만들기
        StoreResponseDTO.MissionPreviewListDTO result = StoreConverter.toMissionPreviewListDTO(missions);

        return ApiResponse.onSuccess(result);


    }
}
