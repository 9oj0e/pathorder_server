package shop.project.pathorderserver.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OptionService {
    private final OptionRepository optionRepository;
}
