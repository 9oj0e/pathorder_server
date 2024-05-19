package shop.project.pathorderserver._core.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.project.pathorderserver._core.errors.exception.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //runtimeException 이 터지만 해당 파일로 오류가 모인다.
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception400 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception401 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(Exception403 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(Exception404 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(Exception500 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiUtil<?> apiUtil = new ApiUtil<>(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);
    }
}
