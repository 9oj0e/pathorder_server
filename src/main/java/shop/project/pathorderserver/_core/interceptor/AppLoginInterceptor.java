package shop.project.pathorderserver._core.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.project.pathorderserver._core.errors.exception.App401;
import shop.project.pathorderserver._core.errors.exception.App500;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.user.SessionUser;

public class AppLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Bearer JWT
        String jwt = request.getHeader("Authorization");
        if (jwt == null) {
            throw new App401("JWT 토큰을 찾을 수 없습니다.");
        }
        jwt = jwt.replace("Bearer ", "");

        try {
            SessionUser sessionUser = JwtUtil.verify(jwt);
            // 임시 세션 (jSessionId는 필요 없음)
            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", sessionUser);
            return true;
        } catch (TokenExpiredException e) {
            throw new App401("토큰이 만료되었습니다. 다시 로그인 하세요.");
        } catch (JWTDecodeException e) {
            throw new App401("유효한 토큰이 아닙니다.");
        } catch (Exception e) {
            throw new App500(e.getMessage()); // 알 수 없는 애러..
        }
    }
}