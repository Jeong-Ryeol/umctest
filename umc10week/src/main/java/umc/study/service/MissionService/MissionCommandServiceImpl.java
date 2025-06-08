package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.ApiMission1.code.MissionRequestDTO;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.exception.GeneralException;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public void completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        memberMission.changeStatus(MissionStatus.COMPLETED);
    }

}