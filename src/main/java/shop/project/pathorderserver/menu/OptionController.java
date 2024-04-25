package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OptionController {
    private final OptionService optionService;
}
