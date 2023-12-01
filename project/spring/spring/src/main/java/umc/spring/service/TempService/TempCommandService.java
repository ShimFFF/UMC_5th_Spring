package umc.spring.service.TempService;

import umc.spring.domain.Users;
import umc.spring.web.dto.MemberRequestDTO;

public interface TempCommandService {
    public Users signUp(MemberRequestDTO.SignUpDTO request);
}
