package umc.spring.Validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.Validation.ChallengingMission;
import umc.spring.service.memberMission.MemberMissionService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ChallengingMissionValidator implements ConstraintValidator<ChallengingMission, Long> {

    private final MemberMissionService missionService;

    @Override
    public void initialize(ChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long memebrMissionId, ConstraintValidatorContext context) {
        boolean isValid = missionService.ismemberMissionChalleng(memebrMissionId);
        if(!isValid){
            context.disableDefaultConstraintViolation(); //기본 제약조건 비활성화
            // ErrorStatus.MISSION_ALREADY_CHALLENGED.toString() : 이미 도전중인 미션입니다.
            // addConstraintViolation() : 에러메시지를 추가
            // buildConstraintViolationWithTemplate() : 에러메시지를 추가할 필드를 지정
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_NOT_CHALLENGED.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
