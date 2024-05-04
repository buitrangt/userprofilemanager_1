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
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Constraint(validatedBy = ValidateDate.DateValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface ValidateDate {

    String message() default "Invalid date format, required ddMMyyyy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String pattern() default "ddMMyyyy";

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ValidateDate[] value();
    }


    class DateValidator implements ConstraintValidator<ValidateDate, String> {

        private String pattern;

        @Override
        public void initialize(ValidateDate constraintAnnotation) {
            this.pattern = constraintAnnotation.pattern();
        }

        @Override
        public boolean isValid(String dateStr, ConstraintValidatorContext constraintValidatorContext) {
            if (dateStr == null || dateStr.isEmpty()) {
                return true; // Use @NotBlank for null/empty checks
            }

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);

            try {
                sdf.parse(dateStr);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
    }
}

