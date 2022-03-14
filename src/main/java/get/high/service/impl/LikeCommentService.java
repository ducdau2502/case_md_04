package get.high.service.impl;

import get.high.model.entity.LikeComment;
import get.high.model.entity.UserInfo;
import get.high.repository.ILikeCommentRepository;
import get.high.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeCommentService implements ILikeCommentService {
    @Autowired
    private ILikeCommentRepository iLikeCommentRepository;

    @Override
    public Iterable<LikeComment> findAll() {
        return iLikeCommentRepository.findAll();
    }

    @Override
    public UserInfo findById(Long id) {
        return iLikeCommentRepository.findById(id);
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return iLikeCommentRepository.save(likeComment);
    }

    @Override
    public void remove(Long id) {
        iLikeCommentRepository.deleteById(id);
    }
}
