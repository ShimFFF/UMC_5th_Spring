package umc.spring.service.MemberService;

import umc.spring.domain.Users;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommendService {
    public Users signUp(MemberRequestDTO.SignUpDTO request);
}
