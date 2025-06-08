package umc.study.ApiMission2.code;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDTO {
    private Long reviewId;
    private String title;
    private String body;
    private Float score;
    private String storeName;
}
