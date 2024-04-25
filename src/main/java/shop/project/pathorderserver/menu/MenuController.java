package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuService menuService;

