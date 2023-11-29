package umc.spring.converter;

import lombok.NonNull;
import lombok.experimental.NonFinal;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.User;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.ReviewRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {

    private static MemberRepository memberRepository;
    private static StoreRepository StoreRepository;

    //의존성 주입
    public ReviewConverter(MemberRepository memberRepository, StoreRepository StoreRepository) {
        this.memberRepository = memberRepository;
        this.StoreRepository = StoreRepository;
    }

    public static StoreReview toReview(ReviewRequest.writeDTO request){

        //User 객체를 찾아서 user에 저장하는 코드
        User user = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 회원이 없습니다."));
        //Store 객체를 찾아서 store에 저장하는 코드
        Store store = StoreRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 가게가 없습니다."));


        return StoreReview.builder()
                .content(request.getContent())
                .user(user)
                .store(store)
                .starPoint(request.getScore())
                .build();
    }
}

