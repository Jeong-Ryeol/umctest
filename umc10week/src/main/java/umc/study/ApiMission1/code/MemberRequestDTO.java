package umc.study.ApiMission1.code;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter // login을 위해 추가
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
        @NotBlank
        @Email
        String email; // login을 위해 추가
        @NotBlank
        String password; // login을 위해 추가
        @NotNull
        Role role; // login을 위해 추가
    }

    @Getter
    @Setter
    public static class LoginRequestDTO{
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}