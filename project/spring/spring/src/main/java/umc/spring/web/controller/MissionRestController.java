package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService;
import umc.spring.service.ReviewService;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/mission") // http://localhost:8080/mission로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionService missionService;

    @PostMapping("") //users/signup으로 접근하면 아래의 메소드를 실행
    public ApiResponse<MissionResponse.addDTO>
    join(@RequestBody @Valid MissionRequest.addDTO request){

        //controller는 api 컨트롤 역할만 하고, 비즈니스 로직은 service에서 처리
        //따라서, response로의 변환은 controller에서,
        // request에서 엔티티 객체로 변환은 service에서 처리
        MemberMission memberMission = missionService.add(request);
        return ApiResponse.onSuccess(MissionConverter.toResponseAddDTO(memberMission));
    }
}
