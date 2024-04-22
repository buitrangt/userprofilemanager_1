package aibles.userprofilemanager_1.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class PasswordValidator implements ConstraintValidator<ValidatePassword, String> {

    private int minLength;
    private boolean requireSpecialChar;
    private boolean requireUpperCase;
    private boolean requireDigit;

    @Override
    public void initialize(ValidatePassword constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.requireSpecialChar = constraintAnnotation.requireSpecialChar();
        this.requireUpperCase = constraintAnnotation.requireUpperCase();
        this.requireDigit = constraintAnnotation.requireDigit();
    }



    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null || password.isEmpty()) {
            return false; // Mật khẩu không được null hoặc trống
        }

        // Kiểm tra độ dài tối thiểu
        if (password.length() < minLength) {
            return false;
        }

        // Kiểm tra yêu cầu ký tự đặc biệt
        if (requireSpecialChar && !password.matches(".*[!@#$%^&*()\\[\\]{}|<>?/\\\\].*")) {
            return false;
        }

        // Kiểm tra yêu cầu chữ cái viết hoa
        if (requireUpperCase && !password.matches(".*[A-Z].*")) {
            return false;
        }

        // Kiểm tra yêu cầu chữ số
        if (requireDigit && !password.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }
}
