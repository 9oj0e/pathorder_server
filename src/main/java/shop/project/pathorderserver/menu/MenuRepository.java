package shop.project.pathorderserver.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    // 매장별 메뉴 리스트
    @Query("select m from Menu m where m.store.id = :storeId")
    Optional<List<Menu>> findAllMenuByStoreId(@Param("storeId") Integer storeId);
}
