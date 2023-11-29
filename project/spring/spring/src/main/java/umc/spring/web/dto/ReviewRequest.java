package umc.spring.web.dto;

import lombok.Getter;


public class ReviewRequest {

    @Getter
    public static class writeDTO{
        private String content;
        private Long userId;
        private Long storeId;
        private Float score;
    }
}
