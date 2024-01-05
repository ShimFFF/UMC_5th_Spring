package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;


public class StoreConverter {


    public static Store toStore(StoreRequest.addDTO request, Users users, Region region){
        return Store.builder()
                .name(request.getStoreName())
                .address(request.getStoreAddress())
                .user(users)
                .region(region)
                .build();
    }

    public static StoreResponse.addDTO toResponseAddDTO (Store store){
        return StoreResponse.addDTO.builder()
                .storeId(store.getStoreId())
                .build();
    }
}
