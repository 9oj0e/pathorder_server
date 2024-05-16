package shop.project.pathorderserver.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<List<Review>> findByUserId(@Param("userId") int userId);
}
