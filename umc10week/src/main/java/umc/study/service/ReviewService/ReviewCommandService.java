package umc.study.service.ReviewService;

import umc.study.ApiMission1.code.ReviewRequestDTO;
import umc.study.domain.Review;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDTO request);
}