package umc.spring.Validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.Validation.ExistStores;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistStoresValidator implements ConstraintValidator<ExistStores, Long>{

    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistStores constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {

        boolean isValid = storeRepository.existsByStoreId(storeId);

        if(isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
        }

        return !isValid;
    }
}
