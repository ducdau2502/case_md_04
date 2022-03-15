package get.high.service;

import get.high.model.entity.Comment;
import get.high.model.entity.Post;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findAllByPost_Id(Long id);

    void deleteAllByPost(Post post);
}
