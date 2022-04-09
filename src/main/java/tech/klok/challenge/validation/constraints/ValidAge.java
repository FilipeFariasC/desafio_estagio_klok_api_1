package tech.klok.challenge.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import tech.klok.challenge.validation.validator.AgeValidator;
/**
 * Classe para validação de idade
 * Deve ser utilizada apenas sobre LocalDate
 * @author filipefariasc
 *
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {
	String message() default "{tech.klok.challenge.validation.constrains.ValidAge}";
	Class<?>[] groups() default { };
	long min() default 18;
	long max() default 65;
	Class<? extends Payload>[] payload() default { };
}
