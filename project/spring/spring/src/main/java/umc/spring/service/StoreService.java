package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequest;
import umc.spring.web.dto.StoreRequest;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Store add(StoreRequest.addDTO request)  {

        Store newStore = StoreConverter.toStore(request);
        return storeRepository.save(newStore);
    }
}
