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
        Optional<List<Review>> reviewListOP = reviewRepository.findByUserId(userId);
        //then
        Assertions.assertThat(reviewListOP.get().size()).isEqualTo(0);

    }
}