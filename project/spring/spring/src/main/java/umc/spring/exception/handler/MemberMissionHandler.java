package umc.spring.exception.handler;

import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}