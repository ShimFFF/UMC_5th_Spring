package umc.spring.web.dto;

import lombok.Getter;

public class StoreRequest {

    @Getter
    public static class addDTO{
        Long memberId;
        Long regionId;
    }
}
