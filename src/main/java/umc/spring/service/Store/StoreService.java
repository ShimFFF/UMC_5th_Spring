package umc.spring.service.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.RegoinHandler;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.store.StoreRequest;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Store add(StoreRequest.addDTO request)  {
        //User 객체를 찾아서 user에 저장하는 코드
        Users users = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        //region 객체를 찾아서 region에 저장하는 코드
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RegoinHandler(ErrorStatus.REGION_NOT_FOUND));

        return storeRepository.save(StoreConverter.toStore(request, users, region));
    }

    public boolean isStoreExist(Long storeId) {

        return storeRepository.existsByStoreId(storeId);
    }

    @Transactional
    public void writeReiew(ReviewRequest.writeDTO request)  {
        //User 객체를 찾아서 user에 저장하는 코드
        Users users = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        //Store 객체를 찾아서 store에 저장하는 코드
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));


        StoreReview newReview = ReviewConverter.toReview(request, users, store);
        reviewRepository.save(newReview);
    }
}
