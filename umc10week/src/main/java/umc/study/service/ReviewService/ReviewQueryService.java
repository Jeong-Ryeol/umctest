package umc.study.service.ReviewService;

import umc.study.ApiMission1.code.StoreResponseDTO;

public interface ReviewQueryService {

    StoreResponseDTO.ReviewPreViewListDTO getMyReviewList(Long memberId, int page);
}
