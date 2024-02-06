package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.Validation.CheckPage;
import umc.spring.Validation.ExistStores;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.Store.StoreQueryService;
import umc.spring.service.Store.StoreService;
import umc.spring.service.review.ReviewQueryService;
import umc.spring.service.review.ReviewService;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/store") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class StoreRestcontroller {

    private final StoreService storeService;
    private final StoreQueryService storeQueryService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/add")
    public ApiResponse<StoreResponse.addDTO>
    join(@RequestBody @Valid StoreRequest.addDTO request){
        Store store = storeService.add(request);

        return ApiResponse.onSuccess(StoreConverter.toResponseAddDTO(store));
    }

    @Operation(summary = "특정 가게 리뷰 쓰기 API",description = "특정 가게에 리뷰를 쓰는 API 입니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "리뷰 작성 완료",responseCode = "200",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "리뷰 작성 실패",responseCode = "400",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    @Parameters(value = {
            @Parameter(name = "userId",description = "유저 아이디",required = true),
            @Parameter(name = "storeId",description = "가게 아이디",required = true),
            @Parameter(name = "content",description = "리뷰 내용",required = true),
            @Parameter(name = "score",description = "리뷰 점수",required = true)
    })
    @PostMapping(value = "/review/write", consumes = "multipart/form-data") // 리뷰 작성 api
    public ApiResponse<String>
    join(@RequestBody @Valid ReviewRequest.writeDTO request){
        return ApiResponse.onSuccess(storeService.writeReiew(request));
    }

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{store-id}/review") // 가게 아이디로 리뷰 조회 api
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponse.StoreReviewPreViewListDTO> getReviewList
    (
            @ExistStores @PathVariable("store-id") Long storeId,
            @CheckPage @RequestParam(name= "page") Integer page
    ){


        return ApiResponse.onSuccess(
                ReviewConverter.toStoreReviewListPreView(
                        reviewQueryService.getStoreReviewList(storeId,page-1)));
    }

    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{store-id}/mission") // 가게 아이디로 미션 조회 api
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponse.StoreMissionPreViewListDTO> getMissionList
            (
                    @ExistStores @PathVariable("store-id") Long storeId,
                    @CheckPage @RequestParam(name= "page") Integer page
            ){


        return ApiResponse.onSuccess(
                MissionConverter.toStoreMissionListPreView(
                        storeQueryService.getMissionList(storeId,page-1)
                ));
    }
}
