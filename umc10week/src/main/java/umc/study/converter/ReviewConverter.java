package umc.study.converter;

import umc.study.ApiMission1.code.ReviewRequestDTO;
import umc.study.ApiMission1.code.ReviewResponseDTO;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO dto, Store store, Member member)
    {
        return Review.builder()
                .store(store)
                .member(member)
                .title(dto.getTitle())
                .score(dto.getScore())
                .body(dto.getBody())
                .build();
    }

    public static ReviewResponseDTO toDTO(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .build();
    }
}
