package shop.project.pathorderserver.store;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import shop.project.pathorderserver._core.utils.ApiUtil;

@RestController
public class StoreSSEController {

    @Scheduled(fixedRate = 1000)
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect() {

        return new SseEmitter();
    }
}
