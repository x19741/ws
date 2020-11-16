package spring.exception;

public class CustomException extends RuntimeException {

    public static String EXCEPTION_MASSAGE="出现了意外的错误，请联系管理员。";

    public CustomException(String message) {
        super(message);
    }
}
