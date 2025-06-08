package umc.study.service.ChallengeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.ApiMission1.code.MissionChallengeRequestDTO;
import umc.study.converter.MissionChallengeConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionChallengeServiceImpl implements MissionChallengeService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(MissionChallengeRequestDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));


        boolean isAlreadyChallenging = memberMissionRepository
                .existsByMemberIdAndMissionIdAndStatus(member.getId(), mission.getId(), MissionStatus.CHALLENGING);

        if (isAlreadyChallenging) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }

        // 도전 정보 저장
        MemberMission memberMission = MissionChallengeConverter.toMemberMission(member, mission);
        return memberMissionRepository.save(memberMission);
    }
}
