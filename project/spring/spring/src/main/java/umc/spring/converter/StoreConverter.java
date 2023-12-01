package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequest;
import umc.spring.web.dto.StoreResponse;

import javax.persistence.EntityNotFoundException;

public class StoreConverter {

    private static MemberRepository memberRepository;
    private static RegionRepository regionRepository;

    //의존성 주입
    public StoreConverter(MemberRepository memberRepository, StoreRepository StoreRepository) {
        this.memberRepository = memberRepository;
        this.regionRepository = regionRepository;
    }

    public static Store toStore(StoreRequest.addDTO request){

        //User 객체를 찾아서 user에 저장하는 코드
        Users users = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 회원이 없습니다."));

        //region 객체를 찾아서 region에 저장하는 코드
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 지역이 없습니다."));

        return Store.builder()
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
