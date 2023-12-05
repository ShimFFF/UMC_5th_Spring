package umc.spring.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.web.dto.store.StoreResponse;

import java.time.LocalDateTime;
import java.util.List;


public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpDTO{
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 내가 진행 중인 미션 조회
    public static class goingMissionPreViewDTO{
        Long missionId;
        Long storeId;
        Integer achievePrice;
        Integer earnPoint;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor // 내가 진행 중인 미션 조회
    // 페이징을 위해 만듬
    public static class goinMissionPreViewListDTO{
        List<goingMissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
