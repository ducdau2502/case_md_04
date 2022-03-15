package get.high.service;

import get.high.model.entity.Post;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByHasTag(String hasTag);


}
