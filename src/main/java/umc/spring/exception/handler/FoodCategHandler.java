package umc.spring.exception.handler;

import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class FoodCategHandler extends GeneralException {
    public FoodCategHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
