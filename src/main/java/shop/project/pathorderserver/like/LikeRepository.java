package shop.project.pathorderserver.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface  LikeRepository extends JpaRepository<Like, Integer> {
    // 특정 사용자가 좋아요한 목록 조회
    @Query("""
            SELECT l.id as id, s.id as storeId, s.imgFilename as storeImgFilename, s.name as storeName
            FROM Like l 
            JOIN l.store s 
            WHERE l.customer.id = :userId
            """)
    List<Object[]> findLikesByUserId(@Param("userId") int userId);

    Optional<Like> findByCustomerIdAndStoreId(int customerId, int storeId);

    int countByStoreId(int storeId);
}
