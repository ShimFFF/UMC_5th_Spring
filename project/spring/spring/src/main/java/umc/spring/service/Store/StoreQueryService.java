package umc.spring.service.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreQueryService {
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Mission> getMissionList(Long storeId, Integer page) { // 가게 미션 리스트
        Optional<Store> optionalStore = findStore(storeId);
        Store store = optionalStore.get();

        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return missionPage;
    }
}
