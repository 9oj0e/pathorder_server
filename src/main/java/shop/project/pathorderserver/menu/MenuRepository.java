package shop.project.pathorderserver.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT m FROM Menu m WHERE m.store.id = :storeId")
        // 매장 메뉴 목록보기
    Optional<List<Menu>> findAllByStoreId(@Param("storeId") Integer storeId);
}
