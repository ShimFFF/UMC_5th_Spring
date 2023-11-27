package umc.spring.ApiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.ApiPayload.code.BaseCode;
import umc.spring.ApiPayload.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccesssStatus implements BaseCode{

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다.");

    // 멤버 관련 응답

    // ~~~ 관련 응답

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
