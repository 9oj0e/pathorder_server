package shop.project.pathorderserver.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.project.pathorderserver._core.errors.exception.App404;

import java.util.List;

@DataJpaTest
public class MenuOptionRepositoryTest {
    @Autowired
    MenuOptionRepository menuOptionRepository;

    @Test // 매장 메뉴 옵션보기
    void findAllByMenuId_test() {
        // given
        Integer menuId = 1;
        // when
        List<MenuOption> menuOptions = menuOptionRepository.findByMenuId(menuId)
                .orElseThrow(() -> new App404("찾을 수 없는 메뉴 옵션"));
        // then
        Assertions.assertThat(menuOptions.size()).isEqualTo(6);
    }

    @Test
    void deleteByMenuId() {
        // given
        int menuId = 1;
        // when
        menuOptionRepository.deleteByMenuId(menuId);
        List<MenuOption> menuOptionList = menuOptionRepository.findAll();
        // then
        Assertions.assertThat(menuOptionList.size()).isEqualTo(153); //144
    }
}
