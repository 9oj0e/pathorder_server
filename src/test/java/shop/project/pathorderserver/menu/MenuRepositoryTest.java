package shop.project.pathorderserver.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.App404;

import java.util.List;

@DataJpaTest
class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;

    @Test // 매장 메뉴 목록보기
    void findAllMenuByStoreId_test() {
        // given
        Integer storeId = 1;
        // when
        List<Menu> menus = menuRepository.findAllByStoreId(storeId)
                .orElseThrow(() -> new App404("찾을 수 없는 메뉴"));
        // then
        Assertions.assertThat(menus.size()).isEqualTo(5);
    }
}