package shop.project.pathorderserver._core.errors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.project.pathorderserver._core.errors.exception.App400;

@Aspect // AOP 등록
@Component // IoC 등록
public class AppValidationHandler {
    // Advice (부가 로직 hello 메서드)
    // Advice가 수행될 위치 == PointCut
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validCheck(JoinPoint jp) {
        Object[] args = jp.getArgs(); // 파라메터(매개변수)
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                if (errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        throw new App400(error.getDefaultMessage()); // + error.getField()
                    }
                }
            }
        }
    }
}
