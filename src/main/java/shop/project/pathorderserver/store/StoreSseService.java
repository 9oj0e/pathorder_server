package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreSseService {
    private final StoreSseRepository storeSSERepository;
    private static final long TIMEOUT = 60 * 1000;
    private static final long RECONNECTION_TIMEOUT = 1000L;
    // OnCompletion: Emitter 가 완료될 때(모든 데이터가 성공적으로 전송된 상태)
    // OnTimeout: Emitter 가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때)

    public SseEmitter createConnection(int storeId) {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        storeSSERepository.save(storeId, emitter);
        // createEvent(storeId, "실시간 Push 서비스 연결 완료"); // EventStream 생성
        return emitter;
    }

    public void createOrderNotification(int orderId, int storeId) {
        if (storeSSERepository.findById(storeId).isPresent()) {
            createEvent(storeId, orderId + "번 주문 알림. 새로고침을 눌러 주문을 확인해주세요.");
        }
    }

    public void createEvent(int storeId, String data) {
        Optional<SseEmitter> opEmitter = storeSSERepository.findById(storeId);
        if (opEmitter.isPresent()) {
            SseEmitter emitter = opEmitter.get();
            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .name("sse")
                    .data(data)
                    .reconnectTime(RECONNECTION_TIMEOUT);
            try {
                emitter.send(event);
                emitter.onTimeout(() -> storeSSERepository.deleteById(storeId));
                emitter.onCompletion(() -> storeSSERepository.deleteById(storeId));
            } catch (Exception e) {
                storeSSERepository.deleteById(storeId);
                emitter.completeWithError(e);
            }
        }
    }
}
