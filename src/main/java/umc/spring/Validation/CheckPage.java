package umc.spring.Validation;

import umc.spring.Validation.Validator.CheckPageValidator;
import umc.spring.Validation.Validator.ExistStoresValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션을 만들 때
@Constraint(validatedBy = CheckPageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "페이지 범위가 0 이하 입니다.";
    Class<?>[] groups() default {}; //그룹핑
    Class<? extends Payload>[] payload() default {}; //메타데이터

}
