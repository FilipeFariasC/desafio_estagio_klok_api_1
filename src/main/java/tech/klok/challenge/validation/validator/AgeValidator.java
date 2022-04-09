package tech.klok.challenge.validation.validator;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import tech.klok.challenge.validation.constraints.ValidAge;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {
	
	private Long min;
	private Long max;
	@Override
	public void initialize(ValidAge constraintAnnotation) {
		min=constraintAnnotation.min();
		max=constraintAnnotation.max();
	}
	
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if(value == null)
			return false;
		
		Integer idade = Period.between(value, LocalDate.now()).getYears();
		
		return (idade >= min && idade <= max);
	}

}
