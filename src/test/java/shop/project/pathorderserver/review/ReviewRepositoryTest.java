package shop.project.pathorderserver.review;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryTest {
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void findByUserId_test() {
        //given
        int userId = 1;
        //when
        Optional<List<Review>> userReviewListOP = reviewRepository.findByUserId(userId);
        //then
        Assertions.assertThat(userReviewListOP.get().size()).isEqualTo(0);

    }

    @Test
    void findByStoreId() {
        int storeId = 1;

        Optional<List<Review>> storeReviewListOP = reviewRepository.findByStoreId(storeId);

        Assertions.assertThat(storeReviewListOP.get().size()).isEqualTo(0);
    }

    // 매장별 리뷰 개수
    @Test
    void findReviewCountByStoreId() {
        int storeId = 1;

        int reviewCount = reviewRepository.findReviewCountByStoreId(storeId);

        Assertions.assertThat(reviewCount).isEqualTo(2);
    }
}