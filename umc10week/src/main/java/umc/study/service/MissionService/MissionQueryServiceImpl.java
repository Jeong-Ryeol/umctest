package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.ApiMission1.code.MissionResponseDTO;
import umc.study.converter.MissionConverter;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResponseDTO.MyMissionPreviewListDTO getMyOngoingMissions(Long memberId, int page) {
        Page<MemberMission> missions = memberMissionRepository
                .findAllByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING, PageRequest.of(page, 10));

        return MissionConverter.toMyMissionPreviewListDTO(missions);
    }
}
