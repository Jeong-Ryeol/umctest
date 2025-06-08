package umc.study.api1.code;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto{
        @NotBlank(message = "공백일 수 없습니다")
        String name;
        @NotNull(message = "널이어서는 안됩니다")
        Integer gender;
        @NotNull(message = "널이어서는 안됩니다")
        Integer birthYear;
        @NotNull(message = "널이어서는 안됩니다")
        Integer birthMonth;
        @NotNull(message = "널이어서는 안됩니다")
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }
}