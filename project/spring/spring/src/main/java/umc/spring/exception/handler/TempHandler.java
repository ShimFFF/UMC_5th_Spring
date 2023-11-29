package umc.spring.exception.handler;

import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

//Handler의 역할 : Exception을 받아서 처리하는 역할
//GereralException을 상속받는 이유 : Exception을 받아서 처리하기 위해서
public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
