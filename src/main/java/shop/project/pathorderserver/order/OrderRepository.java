package shop.project.pathorderserver.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.project.pathorderserver.store.Store;

public interface OrderRepository extends JpaRepository<Store, Integer> {
}
