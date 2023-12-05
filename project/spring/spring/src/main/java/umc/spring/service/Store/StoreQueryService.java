package umc.spring.service.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    public Page<StoreReview> getReviewList(Long storeId, Integer page) {
        Optional<Store> optionalStore = findStore(storeId);
        Store store = optionalStore.get();

        Page<StoreReview> reviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return reviewPage;
    }
}
