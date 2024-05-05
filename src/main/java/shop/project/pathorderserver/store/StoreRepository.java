package shop.project.pathorderserver.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
