package aibles.userprofilemanager_1.exception;

public class PostIdNotFoundException extends RuntimeException {
    public PostIdNotFoundException(String message) {
        super(message);
    }

    // Nếu bạn muốn bao gồm nguyên nhân của ngoại lệ
    public PostIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}