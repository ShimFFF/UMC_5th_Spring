package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.Validation.ExistStores;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Users;
import umc.spring.service.member.MemberCommandServiceImpl;
import umc.spring.service.member.MemberQueryServiceImpl;
import umc.spring.web.dto.member.MemberRequestDTO;
import umc.spring.web.dto.member.MemberResponseDTO;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.store.StoreResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/users") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberCommandServiceImpl memberCommandService;
    private final MemberQueryServiceImpl memberQueryService;

    @PostMapping("/signup") //users/signup으로 접근하면 아래의 메소드를 실행
    public ApiResponse<MemberResponseDTO.SignUpDTO>
            join(@RequestBody @Valid MemberRequestDTO.SignUpDTO request){
        Users users = memberCommandService.signUp(request);
        return ApiResponse.onSuccess(MemberConverter.toSignUpDTO(users));
    }

    @GetMapping("/{user-id}/review") // 유저 아이디로 리뷰 조회 api
    @Operation(summary = "내가 쓴 리뷰 목록 조회 API",description = "내가 쓴 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지"),
    })
    public ApiResponse<ReviewResponse.MyReviewPreViewListDTO>
    getReviewList(@PathVariable("user-id") Long userId,
                  @RequestParam(value = "page", defaultValue = "1") Integer page){
        return ApiResponse.onSuccess(
                ReviewConverter.toMyReviewListPreView(
                        memberQueryService.getReviewList(userId, page)
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
                    @ExistStores @PathVariable("user-id") Long storeId,
                    @RequestParam(value = "page", defaultValue = "1") Integer page
            ){

        return ApiResponse.onSuccess(
                MemberMissionConverter.toGoingMissionPreViewList(
                        memberQueryService.getGoingMissionList(storeId, page)
                )
        );
    }

}
