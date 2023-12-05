package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.store.StoreResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static StoreReview toReview(ReviewRequest.writeDTO request, Users users, Store store){
        return StoreReview.builder()
                .content(request.getContent())
                .user(users)
                .store(store)
                .starPoint(request.getScore())
                .build();
    }

    public static ReviewResponse.StoreReviewPreViewDTO toStoreReviewPreView(StoreReview review){
        return ReviewResponse.StoreReviewPreViewDTO.builder()
                .reviewId(review.getReviewId())
                .memberId(review.getUser().getUserId())
                .memberName(review.getUser().getName())
                .content(review.getContent())
                .starPoint(review.getStarPoint())
                .createdAt(review.getCreatedAt().toLocalDate().toString())
                .build();
    }

    public static ReviewResponse.StoreReviewPreViewListDTO toStoreReviewListPreView(Page<StoreReview> reviewList){
        // stream으로 바꿔서 map으로 하나씩 바꿔줌 -> collect로 다시 list로 바꿔줌 -> 그걸 reviewPreViewDTOList에 넣음
        List<ReviewResponse.StoreReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toStoreReviewPreView).collect(Collectors.toList());

        return ReviewResponse.StoreReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}



