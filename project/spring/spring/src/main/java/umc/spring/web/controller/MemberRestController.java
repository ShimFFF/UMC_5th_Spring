package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.Validation.CheckPage;
import umc.spring.Validation.ExistStores;
import umc.spring.Validation.NotChallengingMission;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService;
import umc.spring.service.member.MemberCommandServiceImpl;
import umc.spring.service.member.MemberQueryServiceImpl;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.memberMission.MemberMissionRequest;
import umc.spring.web.dto.memberMission.MemberMissionResponse;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.store.StoreResponse;

import javax.validation.Valid;

@Slf4j
@RestController
@Validated
@RequestMapping("/users") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberCommandServiceImpl memberCommandService;
    private final MemberQueryServiceImpl memberQueryService;
    private final MemberMissionService memberMissionService;

    @PostMapping("/signup") //users/signup으로 접근하면 아래의 메소드를 실행
    public ApiResponse<MemberResponseDTO.SignUpDTO>
            join(@RequestBody @Valid MemberRequestDTO.SignUpDTO request){
        Users users = memberCommandService.signUp(request);
        return ApiResponse.onSuccess(MemberConverter.toSignUpDTO(users));
    }

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponse.challengeDTO>
    join(@RequestBody @Valid @NotChallengingMission MemberMissionRequest.challengeDTO request){

        //controller는 api 컨트롤 역할만 하고, 비즈니스 로직은 service에서 처리
        //따라서, response로의 변환은 controller에서,
        // request에서 엔티티 객체로 변환은 service에서 처리
        MemberMission memberMission = memberMissionService.challenge(request);
        return ApiResponse.onSuccess(MissionConverter.toResponseAddDTO(memberMission));
    }

    @GetMapping("/{user-id}/review") // 유저 아이디로 리뷰 조회 api
    @Operation(summary = "내가 쓴 리뷰 목록 조회 API",description = "내가 쓴 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 1 페이지부터 시작"),
    })
    public ApiResponse<ReviewResponse.MyReviewPreViewListDTO>
    getReviewList(@PathVariable("user-id") Long userId,
                  @CheckPage @RequestParam(name= "page") Integer page){
        return ApiResponse.onSuccess(
                ReviewConverter.toMyReviewListPreView(
                        memberQueryService.getReviewList(userId, page-1)
                )
        );
    }

    @Operation(summary = "내가 도전 중인 미션 목록 조회 API",description = "내가 도전중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{user-id}/mission") // 가게 아이디로 미션 조회 api
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MemberResponseDTO.goinMissionPreViewListDTO> getMissionList
            (
                    @PathVariable("user-id") Long storeId,
                    @CheckPage @RequestParam(name= "page") Integer page
            ){

        return ApiResponse.onSuccess(
                MemberMissionConverter.toGoingMissionPreViewList(
                        memberQueryService.getGoingMissionList(storeId, page-1)
                )
        );
    }

    @Operation(summary = "미션 완료 API",description = "내가 도전중인 미션을 완료로 바꾸는 API입니다.")
    @PatchMapping("{user-id}/mission/complete/{member-mission-id}")
    public ApiResponse<String> completeMission(
            @PathVariable("user-id") Long userId,
            @PathVariable("member-mission-id") Long memberMissionId
    ){
        memberCommandService.completeMission(userId,memberMissionId);
        return ApiResponse.onSuccess("미션 완료");
    }

}
