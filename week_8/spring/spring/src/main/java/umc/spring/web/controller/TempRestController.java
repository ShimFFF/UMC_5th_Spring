package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.converter.TempConverter;
import umc.spring.service.TempService.TempQueryService;
import umc.spring.web.dto.TempResponse;

@RestController
@RequestMapping("/temp") // http://localhost:8080/temp로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성, 의존성 주입에 사용
public class TempRestController {

    public final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempRequestDTO> testAPI(){

        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        // @RequestParam으로  queryString으로 넘어온 값을 받아옴
        // flag가 1이면 GeneralException을 발생시킴
        tempQueryService.CheckFlag(flag);

        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}

