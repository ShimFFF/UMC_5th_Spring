package umc.spring.service.TempService;

import umc.spring.domain.User;
import umc.spring.web.dto.MemberRequestDTO;

public interface TempCommandService {
    public User signUp(MemberRequestDTO.SignUpDTO request);
}
