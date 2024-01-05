package umc.spring.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.web.dto.review.ReviewResponse;

import java.time.LocalDateTime;
import java.util.List;

public class StoreResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addDTO{
        Long storeId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 가게 별 미션 조회
    public static class StoreMissionPreViewDTO{
        Long missionId;
        Integer achievePrice;
        Integer earnPoint;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 가게 별 미션 리스트
    // 페이징을 위해 만듬
    public static class StoreMissionPreViewListDTO{
        List<StoreMissionPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
