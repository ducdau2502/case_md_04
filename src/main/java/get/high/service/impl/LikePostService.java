package get.high.service.impl;

import get.high.model.entity.LikePost;
import get.high.model.entity.UserInfo;
import get.high.repository.ILikePostRepository;
import get.high.service.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePostService implements ILikePostService {
    @Autowired
    private ILikePostRepository iLikePostRepository;

    @Override
    public Iterable<LikePost> findAll() {
        return iLikePostRepository.findAll();
    }

    @Override
    public UserInfo findById(Long id) {
        return iLikePostRepository.findById(id);
    }

    @Override
    public LikePost save(LikePost likePost) {
        return iLikePostRepository.save(likePost);
    }

    @Override
    public void remove(Long id) {
        iLikePostRepository.deleteById(id);
    }
}
