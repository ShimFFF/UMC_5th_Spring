package umc.spring.Validation;

import umc.spring.Validation.Validator.CategoriesExistValidator;
import umc.spring.Validation.Validator.NotChallengingMissionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션을 만들 때
@Constraint(validatedBy = NotChallengingMissionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotChallengingMission {
    String message() default "이미 도전중인 미션입니다. 미션을 완료하거나 포기해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
