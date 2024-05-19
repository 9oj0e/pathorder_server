package shop.project.pathorderserver.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Repository
public class StoreSseRepository {
    private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();

    public void save(int storeId, SseEmitter emitter) {
        emitters.put(storeId, emitter);
    }

    public Optional<SseEmitter> findById(int storeId) {
        return Optional.ofNullable(emitters.get(storeId));
    }

    public void deleteById(int storeId) {
        emitters.remove(storeId);
    }
}
