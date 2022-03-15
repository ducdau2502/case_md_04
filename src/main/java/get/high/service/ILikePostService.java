package get.high.service;

import get.high.model.entity.LikePost;
import get.high.model.entity.Post;

public interface ILikePostService extends IGeneralService<LikePost> {
    void deleteAllByPost(Post post);
}
