package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver.store.StoreRepository;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
}
