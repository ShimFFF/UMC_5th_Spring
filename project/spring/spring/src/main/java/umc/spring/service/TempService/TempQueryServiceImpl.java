package umc.spring.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1) {
            //에러를 던짐
            //if 내부로 들어가게 되면
            //Service 이후 controller로 가지 않고,
            //바로 Exception handler에 의해 응답이 보내짐
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
            // TempHandler에 의해 에러가 발생
            //ErrorStatus.TEMP_EXCEPTION에 의해 에러가 발생
            // ErrorStatus가 에러를 처리 (에러를 처리하는 방법은 ErrorStatus에 정의되어 있음)
        }
    }
}
