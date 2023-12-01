package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.service.ReviewService;
import umc.spring.web.dto.ReviewRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/review") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping("") //users/signup으로 접근하면 아래의 메소드를 실행
    public ApiResponse<String>
    join(@RequestBody @Valid ReviewRequest.writeDTO request){
        reviewService.write(request);
        return ApiResponse.onSuccess("리뷰 작성 완료");
    }
}
