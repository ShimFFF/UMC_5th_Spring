package umc.spring.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Users;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.member.MemberRequestDTO;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempCommandService{

    private final MemberRepository memberRepository;

    // Member 객체를 생성하는 작업는 Converter를 통해 처리
    // Service는 비즈니스 로직을 처리하는 클래스이므로, 비즈니스 로직을 처리하는 메소드를 생성
    @Override
    public Users signUp(MemberRequestDTO.SignUpDTO request) {

        return null;
    }
}
