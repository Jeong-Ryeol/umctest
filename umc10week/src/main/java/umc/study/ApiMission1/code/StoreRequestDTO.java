package umc.study.ApiMission1.code;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRequestDTO {
    @NotBlank(message = "가게 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "주소는 필수입니다.")
    private String address;

    private Float score;
    private Long regionId;
    private Long userMainId;
}
