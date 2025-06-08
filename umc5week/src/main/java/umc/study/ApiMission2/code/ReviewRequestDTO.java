package umc.study.ApiMission2.code;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

@Getter
public class ReviewRequestDTO {
    @NotNull(message = "storeId는 필수입니다.")
    @ExistStore
    private Long storeId;


    @NotNull(message = "userMainId는 필수입니다.")
    private Long userMainId;

    @NotBlank(message = "리뷰 제목은 필수입니다.")
    private String title;


    @Min(0)
    @Max(5)
    private Float score;

    @NotBlank(message = "리뷰 내용은 필수입니다.")
    private String body;


}