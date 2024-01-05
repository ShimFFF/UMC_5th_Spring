package umc.spring.exception.handler;

import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class RegoinHandler extends GeneralException {
    public RegoinHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
