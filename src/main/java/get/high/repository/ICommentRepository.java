package get.high.repository;

import get.high.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {
}
