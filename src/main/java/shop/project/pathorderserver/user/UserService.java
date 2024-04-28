package shop.project.pathorderserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception400;
import shop.project.pathorderserver._core.errors.exception.Exception401;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.order.Order;
import shop.project.pathorderserver.order.OrderRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional // 회원 가입
    public UserResponse.JoinDTO createUser(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOp = userRepository.findByUsername(reqDTO.getUsername());
        if (userOp.isPresent()) {
            throw new Exception400("중복된 유저입니다.");
        }
        User user = new User(reqDTO);
        userRepository.save(user);

        return UserResponse.JoinDTO
                .builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();
    }

    // 로그인 TODO: 암호화
    public UserResponse.LoginDTO getUser(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
        String jwt = JwtUtil.create(user);

        return UserResponse.LoginDTO // TODO: jwt return
                .builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }

    // 회원정보 보기
    public UserResponse.UserDTO getUser(int sessionUserId) {
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));

        return UserResponse.UserDTO
                .builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .tel(user.getTel())
                .email(user.getEmail())
                .imgFilename(user.getImgFilename())
                .build();
    }

    @Transactional // 회원정보 수정
    public UserResponse.UpdateDTO setUser(UserRequest.UpdateDTO reqDTO, int sessionUserId) {
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));
        user.update(reqDTO);

        return UserResponse.UpdateDTO
                .builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .tel(user.getTel())
                .email(user.getEmail())
                .build();
    }

    @Transactional // 사진 업로드 (업로드/수정/삭제)
    public UserResponse.ImgDTO setImg(UserRequest.ImgDTO reqDTO, int sessionUserId) throws IOException {
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        String encodedData = reqDTO.getEncodedImg();
        byte[] decodedByte = Base64.getDecoder().decode(encodedData);
        String newFilename = reqDTO.getUsername() + "_" + UUID.randomUUID() + ".jpg"; // username_UUID.jpg
        Path newFilePath = Paths.get("./upload/" + newFilename);
        Files.write(newFilePath, decodedByte);
        user.setImgFilename(newFilename);

        return new UserResponse.ImgDTO(newFilePath.toString());
    }

    // 주문내역 목록보기
    public UserResponse.OrderListDTO getOrderList(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));

        return new UserResponse.OrderListDTO(orders);
    }

    // 주문 상세보기
    @Transactional
    public UserResponse.OrderDetailDTO getOrderDetail(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));

        return new UserResponse.OrderDetailDTO(order);
    }
}
