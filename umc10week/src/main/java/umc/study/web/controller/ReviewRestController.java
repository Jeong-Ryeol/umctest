package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiMission1.code.ReviewRequestDTO;
import umc.study.ApiMission1.code.ReviewResponseDTO;
import umc.study.ApiMission1.code.StoreResponseDTO;
import umc.study.ApiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.exception.general.PageValidationException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Validated
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/create")
    public ApiResponse<ReviewResponseDTO> createReview(@RequestBody @Valid ReviewRequestDTO request) {
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toDTO(review));
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "요청 헤더에 memberId를 넣고, page는 1 이상이어야 합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON4004", description = "페이지는 1 이상이어야 합니다.")
    })
    @GetMapping("/me")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(name = "page") Integer page) {

        if (page == null || page < 1) {
            throw new PageValidationException();
        }

        return ApiResponse.onSuccess(
                reviewQueryService.getMyReviewList(memberId, page - 1)
        );
    }
}
