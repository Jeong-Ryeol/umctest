package umc.study.ApiMission1.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class MissionResponseDTO {

    private Long missionId;
    private String missionSpec;
    private Integer reward;
    private String storeName;

    //단일 미션 정보 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionPreviewDTO {
        private Long missionId;
        private String missionSpec;
        private Integer reward;
        private String storeName;
    }

    // 진행 중 미션 목록 + 페이징 DTO
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyMissionPreviewListDTO {
        private List<MyMissionPreviewDTO> myMissionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
