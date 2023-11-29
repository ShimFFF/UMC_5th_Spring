package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.converter.FoodPreferConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.FoodCateg;
import umc.spring.domain.StoreReview;
import umc.spring.domain.User;
import umc.spring.domain.mapping.FoodPerfer;
import umc.spring.exception.handler.FoodCategHandler;
import umc.spring.repository.FoodCategRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.ReviewRequest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void write(ReviewRequest.writeDTO request)  {

        StoreReview newReview = ReviewConverter.toReview(request);
        reviewRepository.save(newReview);
    }

}
