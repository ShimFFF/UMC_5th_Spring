package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.RegoinHandler;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequest;
import umc.spring.web.dto.StoreResponse;

import javax.persistence.EntityNotFoundException;


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
