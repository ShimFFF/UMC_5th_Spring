package umc.spring.Validation;

import umc.spring.Validation.Validator.ChallengingMissionValidator;
import umc.spring.Validation.Validator.NotChallengingMissionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션을 만들 때
@Constraint(validatedBy =  ChallengingMissionValidator.class)
@Target( {ElementType.TYPE_USE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ChallengingMission {
    String message() default "도전 중인 미션이 아닙니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
