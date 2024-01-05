package umc.spring.exception.handler;

import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}