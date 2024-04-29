package shop.project.pathorderserver.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Integer> {

    @Query("SELECT o FROM MenuOption o WHERE o.menu.id = :menuId") // 매장 메뉴 옵션 목록보기
    Optional<List<MenuOption>> findByMenuId(@Param("menuId") Integer menuId);
}
