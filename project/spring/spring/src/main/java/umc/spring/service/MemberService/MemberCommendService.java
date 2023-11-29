package umc.spring.service.MemberService;

import umc.spring.domain.User;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommendService {
    public User signUp(MemberRequestDTO.SignUpDTO request);
}
