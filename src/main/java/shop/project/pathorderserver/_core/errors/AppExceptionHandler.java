package shop.project.pathorderserver._core.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.project.pathorderserver._core.errors.exception.*;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RestControllerAdvice //runtimeException 이 터지만 해당 파일로 오류가 모인다.
public class AppExceptionHandler {

    @ExceptionHandler(App400.class)
    public ResponseEntity<?> ex400(App400 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(App401.class)
    public ResponseEntity<?> ex401(App401 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(App403.class)
    public ResponseEntity<?> ex403(App403 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(App404.class)
    public ResponseEntity<?> ex404(App404 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(App500.class)
    public ResponseEntity<?> ex500(App500 e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());

        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
