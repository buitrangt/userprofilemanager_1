package aibles.userprofilemanager_1.validation;



import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidatePhone.PhoneNumberValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidatePhone {

    String message() default "Invalid Phone Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PhoneNumberValidator implements ConstraintValidator<ValidatePhone, String> {

        @Override
        public void initialize(ValidatePhone constraintAnnotation) {}

        @Override
        public boolean isValid(
                String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
            var regexPhoneNumber = "(0)(3|5|7|8|9)+([0-9]{8})\\b";
            return phoneNumber.matches(regexPhoneNumber);
        }
    }
}

