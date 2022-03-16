package get.high.service;

import get.high.model.entity.Post;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByHasTag(String hasTag);

    Iterable<Post> findAllByUserInfo_Id(Long userinfo_id);

    Iterable<Post> findAllByStatus(Integer number);
}
