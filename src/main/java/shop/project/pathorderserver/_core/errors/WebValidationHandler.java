package shop.project.pathorderserver._core.errors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.project.pathorderserver._core.errors.exception.Web400;

@Aspect // AOP 등록
@Component // IoC 등록
public class WebValidationHandler {
    // Advice (부가 로직 hello 메서드)
    // Advice가 수행될 위치 == PointCut
    @Before("@annotation(shop.project.pathorderserver._core.errors.WebRequest)")
    public void validCheck(JoinPoint jp) {
        System.out.println("webValidationHandler.validCheck() 호출");
        Object[] args = jp.getArgs(); // 파라메터(매개변수)
        for (Object arg : args) {
            if (arg instanceof Errors errors) {
                if (errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        throw new Web400(error.getDefaultMessage()); // + error.getField()
                    }
                }
            }
        }
    }
}
