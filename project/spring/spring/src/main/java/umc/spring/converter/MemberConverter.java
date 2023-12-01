package umc.spring.converter;

import umc.spring.domain.Users;
import umc.spring.domain.enums.SocialType;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

// converter는 Member 객체를 생성하는 작업을 처리하는 클래스
public class MemberConverter {

    public static MemberResponseDTO.SignUpDTO toSignUpDTO(Users users){
        return MemberResponseDTO.SignUpDTO.builder()
                .memberId(users.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Users toMember(MemberRequestDTO.SignUpDTO request){
        return Users.builder()
                .email(request.getEmail())
                .name(request.getName())
                .gender(request.getGender())
                .phone(request.getPhone())
                .birth(request.getBrith())
                .address(request.getAddress())
                .socialType(toSocialType(request.getSocialType()))
                .foodPerferList(new ArrayList<>())
                .build();
    }

    public static SocialType toSocialType(Integer socialType){
        switch (socialType) {
            case 1:  return SocialType.KAKAO;
            case 2: return SocialType.NAVER;
            case 3: return SocialType.APPLE;
            case 4: return SocialType.GOOGLE;
        }

        return SocialType.NONE;
    }
}
