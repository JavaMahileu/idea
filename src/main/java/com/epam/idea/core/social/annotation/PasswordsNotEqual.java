package com.epam.idea.core.social.annotation;

/**
 * Created by Ihar_Niakhlebau on 12-Jun-15.
 */
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.epam.idea.core.social.validator.PasswordsNotEqualValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordsNotEqualValidator.class)
@Documented
public @interface PasswordsNotEqual {

	String message() default "PasswordsNotEqual";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String passwordFieldName() default "";

	String passwordVerificationFieldName() default "";
}
