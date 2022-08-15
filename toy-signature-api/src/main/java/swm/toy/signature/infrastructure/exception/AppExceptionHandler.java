package swm.toy.signature.infrastructure.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorMessages> handleAppException(AppException exception) {
        return responseErrorMessages(
                List.of(exception.getMessage()), exception.getErrorCode().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessages> handleValidationError(
            MethodArgumentNotValidException exception) {
        List<String> messages =
                exception.getBindingResult().getFieldErrors().stream()
                        .map(this::createFieldErrorMessage)
                        .collect(Collectors.toList());

        return responseErrorMessages(messages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    //    @ExceptionHandler(Exception.class)
    //    public ResponseEntity<ErrorMessages> handleException(Exception exception) {
    //        return responseErrorMessages(List.of("Internal Server Error", exception.getMessage()),
    // HttpStatus.UNPROCESSABLE_ENTITY);
    //    }

    private ResponseEntity<ErrorMessages> responseErrorMessages(
            List<String> messages, HttpStatus status) {
        System.out.println(messages);
        ErrorMessages errorMessages = new ErrorMessages(status.value());
        messages.forEach(errorMessages::append);
        return new ResponseEntity<>(errorMessages, status);
    }

    private String createFieldErrorMessage(FieldError fieldError) {
        return "["
                + fieldError.getField()
                + "] must be "
                + fieldError.getDefaultMessage()
                + ". your input: ["
                + fieldError.getRejectedValue()
                + "]";
    }
}
