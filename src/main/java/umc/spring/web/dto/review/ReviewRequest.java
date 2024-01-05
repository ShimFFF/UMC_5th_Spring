package umc.spring.web.dto.review;

import lombok.Getter;
import umc.spring.Validation.ExistStores;

import java.awt.datatransfer.FlavorEvent;


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
