package shop.project.pathorderserver._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.project.pathorderserver._core.errors.exception.*;

@ControllerAdvice //runtimeException 이 터지만 해당 파일로 오류가 모인다.
public class WebExceptionHandler {

    @ExceptionHandler(Web400.class)
    public String ex400(Web400 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        request.setAttribute("status", 400);

        return "error";
    }

    @ExceptionHandler(Web401.class)
    public String ex401(Web401 e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        request.setAttribute("status", 401);

        return "login-form";
    }

    @ExceptionHandler(Web403.class)
    public String ex403(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        request.setAttribute("status", 403);

        return "error";
    }

    @ExceptionHandler(Web404.class)
    public String ex404(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        request.setAttribute("status", 404);

        return "error";
    }

    @ExceptionHandler(Web500.class)
    public String ex500(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        request.setAttribute("status", 500);

        return "error";
    }
}
