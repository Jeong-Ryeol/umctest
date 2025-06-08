package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.validation.annotation.PageValidation;

public class PageValidator implements ConstraintValidator<PageValidation, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page != null && page >= 1;
    }
}
