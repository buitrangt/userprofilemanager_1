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

@Constraint(validatedBy = PasswordValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidatePassword {

    String message() default "Invalid password format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minLength() default 8; // Độ dài tối thiểu của mật khẩu
    boolean requireSpecialChar() default true; // Yêu cầu mật khẩu chứa ít nhất một ký tự đặc biệt
    boolean requireUpperCase() default true; // Yêu cầu mật khẩu chứa ít nhất một chữ cái viết hoa
    boolean requireDigit() default true; // Yêu cầu mật khẩu chứa ít nhất một chữ số
}
