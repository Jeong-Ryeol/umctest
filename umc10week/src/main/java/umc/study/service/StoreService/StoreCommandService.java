package umc.study.service.StoreService;

import umc.study.ApiMission1.code.StoreRequestDTO;
import umc.study.domain.Store;

public interface StoreCommandService {
    Store createStore(StoreRequestDTO request);
}