package umc.spring.Validation;

import umc.spring.Validation.Validator.ExistStoresValidator;
import umc.spring.Validation.Validator.NotChallengingMissionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션을 만들 때
@Constraint(validatedBy = ExistStoresValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStores {
    String message() default "해당하는 가게가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
