package umc.spring.Validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.Validation.NotChallengingMission;
import umc.spring.service.MemberMissionService;
import umc.spring.web.dto.memberMission.MemberMissionRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class NotChallengingMissionValidator implements ConstraintValidator<NotChallengingMission, MemberMissionRequest.challengeDTO> {

    private final MemberMissionService missionService;

    @Override
    public void initialize(NotChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequest.challengeDTO request, ConstraintValidatorContext context) {
        boolean isValid = missionService.ismemberMissionchallengeValid(request);
        if(!isValid){
            context.disableDefaultConstraintViolation(); //기본 제약조건 비활성화
            // ErrorStatus.MISSION_ALREADY_CHALLENGED.toString() : 이미 도전중인 미션입니다.
            // addConstraintViolation() : 에러메시지를 추가
            // buildConstraintViolationWithTemplate() : 에러메시지를 추가할 필드를 지정
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
