package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.StoreReview;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.ReviewRequest;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public void write(ReviewRequest.writeDTO request)  {

        StoreReview newReview = ReviewConverter.toReview(request);
        reviewRepository.save(newReview);
    }

}
