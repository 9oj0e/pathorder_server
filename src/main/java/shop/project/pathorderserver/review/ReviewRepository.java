package shop.project.pathorderserver.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // 내 리뷰 보기
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId ORDER BY r.createdAt DESC")
    Optional<List<Review>> findByUserId(@Param("userId") int userId);

    // 매장 리뷰 보기
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.createdAt DESC")
    Optional<List<Review>> findByStoreId(@Param("storeId") int storeId);
}
