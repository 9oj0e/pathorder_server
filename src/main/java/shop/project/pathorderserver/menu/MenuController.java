package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MenuController {
    private final MenuService menuService;
}
