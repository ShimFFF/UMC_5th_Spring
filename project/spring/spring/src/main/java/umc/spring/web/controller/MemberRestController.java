package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Users;
import umc.spring.service.MemberService.MemberCommandServiceImpl;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/users") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberCommandServiceImpl memberCommandService;

    @PostMapping("/signup") //users/signup으로 접근하면 아래의 메소드를 실행
    public ApiResponse<MemberResponseDTO.SignUpDTO>
            join(@RequestBody @Valid MemberRequestDTO.SignUpDTO request){
        Users users = memberCommandService.signUp(request);
        return ApiResponse.onSuccess(MemberConverter.toSignUpDTO(users));
    }

}
