package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.web.dto.review.ReviewRequest;

public class ReviewConverter {

    public static StoreReview toReview(ReviewRequest.writeDTO request, Users users, Store store){


        return StoreReview.builder()
                .content(request.getContent())
                .user(users)
                .store(store)
                .starPoint(request.getScore())
                .build();
    }
}

