package get.high.repository;

import get.high.model.entity.LikePost;
import get.high.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
    void deleteAllByPost(Post post);
}
