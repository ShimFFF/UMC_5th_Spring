package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.Validation.ExistStores;


public class ReviewRequest {

    @Getter
    public static class writeDTO{
        private String content;
        private Long userId;
        @ExistStores
        private Long storeId;
        private Float score;
    }
}
