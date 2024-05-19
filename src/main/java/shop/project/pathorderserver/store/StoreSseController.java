package shop.project.pathorderserver.store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@RestController
public class StoreSseController {
    private final HttpSession session;
    private final StoreSseService storeSSEService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect() {
        SessionStore sessionStore = (SessionStore) session.getAttribute("sessionStore");
        if (sessionStore == null) {
            return null;
        } else {
            return storeSSEService.createConnection(sessionStore.getId());
        }
    }
}
