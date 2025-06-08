package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.study.ApiMission1.code.MissionResponseDTO;

public interface MissionQueryService {
    //Page<MissionResponseDTO> getOngoingMissions(Long memberId, int page);

    MissionResponseDTO.MyMissionPreviewListDTO getMyOngoingMissions(Long memberId, int page);


}
