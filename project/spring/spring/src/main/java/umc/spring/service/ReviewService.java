package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequest;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository StoreRepository;

    @Transactional
    public void write(ReviewRequest.writeDTO request)  {
        //User 객체를 찾아서 user에 저장하는 코드
        Users users = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        //Store 객체를 찾아서 store에 저장하는 코드
        Store store = StoreRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));


        StoreReview newReview = ReviewConverter.toReview(request, users, store);
        reviewRepository.save(newReview);
    }

}
