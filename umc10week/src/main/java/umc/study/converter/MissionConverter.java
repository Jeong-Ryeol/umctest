package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.ApiMission1.code.MissionRequestDTO;
import umc.study.ApiMission1.code.MissionResponseDTO;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO dto, Store store) {
        return Mission.builder()
                .missionSpec(dto.getMissionSpec())
                .reward(dto.getReward())
                .store(store)
                .deadline(dto.getDeadline())
                .build();
    }

    public static MissionResponseDTO toResponseDTO(Mission mission) {
        return MissionResponseDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .storeName(mission.getStore().getName())
                .build();
    }

    // 단일 Mission → MyMissionPreviewDTO 변환
    public static MissionResponseDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MyMissionPreviewDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .storeName(mission.getStore().getName())
                .build();
    }

    // Page<MemberMission> → MyMissionPreviewListDTO 변환
    public static MissionResponseDTO.MyMissionPreviewListDTO toMyMissionPreviewListDTO(Page<MemberMission> memberMissionPage) {
        List<MissionResponseDTO.MyMissionPreviewDTO> dtoList = memberMissionPage.stream()
                .map(mm -> toMyMissionPreviewDTO(mm.getMission()))
                .collect(Collectors.toList());

        return MissionResponseDTO.MyMissionPreviewListDTO.builder()
                .myMissionList(dtoList)
                .listSize(dtoList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
