package shop.project.pathorderserver.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.project.pathorderserver.store.StoreRepository;
import shop.project.pathorderserver.user.UserRepository;

@RequiredArgsConstructor
@Service
public class LikeService {
    final private UserRepository userRepository;
    final private StoreRepository storeRepository;

}
