package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.ApiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.Store.StoreService;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/store") // http://localhost:8080/users로 접근하면 아래의 메소드를 실행
@RequiredArgsConstructor
public class StoreRestcontroller {

    private final StoreService storeService;

    @PostMapping("/add")
    public ApiResponse<StoreResponse.addDTO>
    join(@RequestBody @Valid StoreRequest.addDTO request){
        Store store = storeService.add(request);

        return ApiResponse.onSuccess(StoreConverter.toResponseAddDTO(store));
    }
}
