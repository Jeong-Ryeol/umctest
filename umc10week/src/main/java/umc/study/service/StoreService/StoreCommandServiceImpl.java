package umc.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.ApiMission1.code.StoreRequestDTO;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.domain.UserMain;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.UserMainRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final UserMainRepository userMainRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));

        UserMain userMain = userMainRepository.findById(request.getUserMainId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Store store = StoreConverter.toStore(request, region, userMain);
        return storeRepository.save(store);
    }
}
