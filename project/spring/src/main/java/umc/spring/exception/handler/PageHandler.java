package umc.spring.exception.handler;

import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
