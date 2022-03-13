package get.high.repository;

import get.high.model.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends CrudRepository<Post, Long> {
}
