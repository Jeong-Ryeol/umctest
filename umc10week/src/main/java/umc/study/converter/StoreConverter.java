package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.ApiMission1.code.StoreRequestDTO;
import umc.study.ApiMission1.code.StoreResponseDTO;
import umc.study.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    public static Store toStore(StoreRequestDTO dto, Region region, UserMain userMain) {
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .score(dto.getScore())
                .region(region)
                .userMain(userMain)
                .build();
    }

    public static StoreResponseDTO toStoreResponseDTO(Store store) {
        return StoreResponseDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .score(store.getScore())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(
                        review.getMember() != null ? review.getMember().getName() : "알 수 없음"
                )
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList) {
        return reviewPreViewListDTO(reviewList);
    }

    public static StoreResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return StoreResponseDTO.MissionPreviewDTO.builder()
                .missionSpec(mission.getMissionSpec())
                .price(mission.getPrice())
                .reward(mission.getReward())
                .build();
    }

    public static StoreResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missionPage) {

        List<StoreResponseDTO.MissionPreviewDTO> dtoList = missionPage.stream()
                .map(StoreConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return StoreResponseDTO.MissionPreviewListDTO.builder()
                .missionList(dtoList)
                .listSize(dtoList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

}