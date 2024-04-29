package shop.project.pathorderserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.project.pathorderserver._core.errors.exception.Exception400;
import shop.project.pathorderserver._core.errors.exception.Exception401;
import shop.project.pathorderserver._core.errors.exception.Exception404;
import shop.project.pathorderserver._core.utils.JwtUtil;
import shop.project.pathorderserver.order.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;

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

    @Transactional // 사진 업로드
    public UserResponse.ImgDTO setImg(UserRequest.ImgDTO reqDTO, int sessionUserId) throws IOException {
        // 회원 조회
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        // 기본 파일 이름 설정 TODO: 파일 변경 시, 삭제 후, 기본 이미지로 변경하는 로직 추가하기
        // String defaultImgFilename = "default/avatar.png";
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

    @Transactional // 주문내역 상세보기
    public UserResponse.OrderDetailDTO getOrderDetail(int orderId) {
        List<Integer> orderMenuIdList; // 34, 35, 36
        Order order // 단일 주문 내역 조회
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        List<OrderMenu> orderMenus // 주문 내역의 메뉴 목록.
                = orderMenuRepository.findAllByOrderId(orderId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 주문입니다."));
        /* 양방향 매핑으로 변경.
        orderMenuIdList // 주문 메뉴별 Id 추출
                = orderMenus.stream().map(orderMenu -> orderMenu.getId()).toList();
        for (int orderMenuId : orderMenuIdList) {
            List<OrderOption> orderOptions // 주문 메뉴별 옵션 목록 조회
                    = orderMenuRepository.findAllOptionByMenuId(orderMenuId)
                            .orElse(null); // 없으면 null
            orderMenus.get(orderMenuId).setOrderOption(orderOptions);
        }
        */
        return new UserResponse.OrderDetailDTO(order, orderMenus);
    }
}
