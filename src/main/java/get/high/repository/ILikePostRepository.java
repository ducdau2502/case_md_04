package get.high.repository;

import get.high.model.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
}
