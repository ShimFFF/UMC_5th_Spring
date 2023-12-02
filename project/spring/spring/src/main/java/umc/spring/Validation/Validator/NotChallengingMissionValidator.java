package umc.spring.Validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.Validation.NotChallengingMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.web.dto.MissionRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class NotChallengingMissionValidator implements ConstraintValidator<NotChallengingMission, MissionRequest.challengeDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(NotChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequest.challengeDTO request, ConstraintValidatorContext context) {
        boolean isValid = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(request.getMemberId(), request.getMissionId());

        if(isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
        }

        return !isValid;
    }
}
