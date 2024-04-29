package shop.project.pathorderserver.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;

    @Test
    void findAllMenuByStoreId_test() {
        // given
        Integer storeId = 1;
        // when
        Optional<List<Menu>> menuList = menuRepository.findAllMenuByStoreId(storeId);
        // then
        Assertions.assertThat(menuList.get().size()).isEqualTo(5);
    }

    @Test
    void findOptionByMenuId_test() {
        // given
        Integer menuId = 1;
        // when
        Optional<List<MenuOption>> optionOP = menuRepository.findOptionByMenuId(menuId);
        // then
        Assertions.assertThat(optionOP.get().size()).isEqualTo(6);
    }
}