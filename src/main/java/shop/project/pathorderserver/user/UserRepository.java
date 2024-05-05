package shop.project.pathorderserver.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // TODO: 암호화
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<User> findByUsername(@Param("username") String username);
}
