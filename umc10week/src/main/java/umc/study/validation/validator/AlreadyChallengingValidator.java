package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MemberMissionRepository;
import umc.study.validation.annotation.AlreadyChallenging;

@Component
@RequiredArgsConstructor
public class AlreadyChallengingValidator implements ConstraintValidator<AlreadyChallenging, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(AlreadyChallenging constraintAnnotation) {
        // 기본 초기화
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long memberId = 1L; // 인증 미적용 상태이므로 하드코딩

        boolean isChallenging = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                memberId, missionId, MissionStatus.CHALLENGING
        );

        if (isChallenging) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGED.toString())
                    .addConstraintViolation();
        }

        return !isChallenging;
    }
}
