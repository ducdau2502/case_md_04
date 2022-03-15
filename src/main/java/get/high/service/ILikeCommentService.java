package get.high.service;

import get.high.model.entity.Comment;
import get.high.model.entity.LikeComment;

public interface ILikeCommentService extends IGeneralService<LikeComment> {
    void deleteAllByComment(Comment comment);
}
