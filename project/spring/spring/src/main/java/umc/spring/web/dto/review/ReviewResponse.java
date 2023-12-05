package umc.spring.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 가게 별 리뷰 조회
    public static class StoreReviewPreViewDTO{
        Long reviewId;
        Long memberId;
        String memberName;
        String content;
        Float starPoint;
        String createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 가게 별 리뷰 리스트
    // 페이징을 위해 만듬
    public static class StoreReviewPreViewListDTO{
        List<StoreReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 내가 쓴 리뷰 조회
    public static class MyReviewPreViewDTO{
        Long reviewId;
        Long storeId;
        String storeName;
        String content;
        Float starPoint;
        String createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 내가 쓴 리뷰 리스트
    // 페이징을 위해 만듬
    public static class MyReviewPreViewListDTO{
        List<MyReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
