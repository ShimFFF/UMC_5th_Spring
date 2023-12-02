package umc.spring.Validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.Validation.ExistCategories;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.repository.FoodCategRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final FoodCategRepository foodCategoryRepository;
    //Repository는 서비스에서만 사용되어야 함
    //따라서, 서비스에서 해당 어노테이션을 사용할 때, 해당 어노테이션을 사용하는 필드에 대한 검증을 서비스에서 진행해야 함


    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> foodCategoryRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
