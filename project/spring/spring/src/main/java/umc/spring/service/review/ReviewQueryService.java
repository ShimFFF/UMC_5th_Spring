package umc.spring.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<StoreReview> getStoreReviewList(Long storeId, Integer page) {
        Optional<Store> optionalStore = findStore(storeId);
        Store store = optionalStore.get();

        Page<StoreReview> reviewPage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return reviewPage;
    }


}
