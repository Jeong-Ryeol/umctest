package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.ApiMission1.code.StoreResponseDTO;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    List<Store> findStoresByNameAndScore(String name, Float score);

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Mission> getMissionListByStore(Long storeId, int page);

}