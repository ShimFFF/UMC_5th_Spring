package umc.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.ApiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {


    private BaseErrorCode code; // 추상화 시킨 ErrorCode를 받아서 처리

    public ErrorReasonDTO getErrorReason() {

        return this.code.getReason(); // 추상화 시킨 ErrorCode의 getReason()을 호출
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){

        return this.code.getReasonHttpStatus(); // 추상화 시킨 ErrorCode의 getReasonHttpStatus()를 호출
    }

}
