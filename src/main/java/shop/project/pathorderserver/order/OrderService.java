package shop.project.pathorderserver.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver.menu.Menu;
import shop.project.pathorderserver.menu.MenuRepository;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
}
