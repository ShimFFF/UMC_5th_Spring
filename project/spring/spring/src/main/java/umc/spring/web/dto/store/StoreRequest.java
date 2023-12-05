package umc.spring.web.dto.store;

import lombok.Getter;

public class StoreRequest {

    @Getter
    public static class addDTO{
        Long memberId;
        Long regionId;
        String storeName;
        String storeAddress;
    }
}
