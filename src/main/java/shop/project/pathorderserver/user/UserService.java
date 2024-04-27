package shop.project.pathorderserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional // 회원 가입
    public UserResponse.JoinDTO createUser(UserRequest.JoinDTO reqDTO) {
        /*
        Optional<User> userOp = userRepository.findByUsername(reqDTO.getUsername());
        if (userOp.isPresent()) {
            throw new Exception400 ("중복된 유저입니다.");
        }
        User user = new User(reqDTO);
        userRepository.save(user);
        */
        return null;
    }

    // 로그인 TODO: 암호화
    public UserResponse.LoginDTO getUser(UserRequest.LoginDTO reqDTO) {
        /*
        User user = userRepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 또는 비밀번호가 틀렸습니다."));
        String jwt = JwtUtil.create(user);
        */
        return null;
    }

    // 회원정보 보기
    public UserResponse.UserDTO getUser(int userId) {
        /*
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        */
        return null;
    }

    @Transactional // 회원정보 수정
    public UserResponse.UpdateDTO setUser(UserRequest.UpdateDTO reqDTO) {
        /*
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("회원 정보를 찾을 수 없습니다."));
        user.update(reqDTO);
        */
        return null;
    }

    @Transactional // 사진 업로드
    public void setImg(UserRequest.ImgDTO reqDTO, int userId) throws IOException {
        /*
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("찾을 수 없는 유저입니다."));
        String encodedData = reqDTO.getEncodedImg();
        byte[] decodedByte = Base64.getDecoder().decode(encodedData);
        String newFilename = reqDTO.getUsername() + "_" + UUID.randomUUID() + ".jpg"; // username_UUID.jpg
        Path newFilePath = Paths.get("./upload/" + newFilename);
        Files.write(newFilePath, decodedByte);
        user.setImgFilename(newFilename);

        return new UserResponse.ImgDTO(reqDTO.getFileName(), newFilePath.toString());
        */
    }
}
