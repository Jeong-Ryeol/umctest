package umc.study.ApiMission1.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ReviewPreViewDTO {
    MemberRequestDTO memberinfo;
    Float score;
    String body;
    LocalDate createdAt;
}

