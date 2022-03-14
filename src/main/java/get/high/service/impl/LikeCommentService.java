package get.high.service.impl;

import get.high.model.entity.LikeComment;
import get.high.repository.ILikeCommentReposiroty;
import get.high.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentService implements ILikeCommentService {
    @Autowired
    private ILikeCommentReposiroty iLikeCommentReposiroty;

    @Override
    public Iterable<LikeComment> findAll() {
        return iLikeCommentReposiroty.findAll();
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return iLikeCommentReposiroty.findById(id);
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return iLikeCommentReposiroty.save(likeComment);
    }

    @Override
    public void remove(Long id) {
        iLikeCommentReposiroty.deleteById(id);
    }
}
