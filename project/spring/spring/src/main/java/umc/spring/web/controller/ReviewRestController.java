package umc.spring.web.controller;

import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.Validation.ExistStores;
import umc.spring.converter.ReviewConverter;
import umc.spring.service.review.ReviewQueryService;
import umc.spring.service.review.ReviewService;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/review") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "특정 가게 리뷰 쓰기 API",description = "특정 가게에 리뷰를 쓰는 API 입니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "리뷰 작성 완료",responseCode = "200",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "리뷰 작성 실패",responseCode = "400",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @Parameters(value = {
            @Parameter(name = "userId",description = "유저 아이디",required = true),
            @Parameter(name = "storeId",description = "가게 아이디",required = true),
            @Parameter(name = "content",description = "리뷰 내용",required = true),
            @Parameter(name = "score",description = "리뷰 점수",required = true)
    })
    @PostMapping("/write") // 리뷰 작성 api
    public ApiResponse<String>
    join(@RequestBody @Valid ReviewRequest.writeDTO request){
        reviewService.write(request);
        return ApiResponse.onSuccess("리뷰 작성 완료");
    }

    //로깅 설정
    @GetMapping("/{store-id}") // 가게 아이디로 리뷰 조회 api
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponse.StoreReviewPreViewListDTO> getReviewList
    (
            @ExistStores @PathVariable("store-id") Long storeId,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ){


        return ApiResponse.onSuccess(
                ReviewConverter.toStoreReviewListPreView(
                        reviewQueryService.getReviewList(storeId,page)));
    }

}
