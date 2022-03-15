package get.high.service.impl;

import get.high.model.entity.Comment;
import get.high.model.entity.LikeComment;
import get.high.repository.ILikeCommentRepository;
import get.high.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentService implements ILikeCommentService {
    @Autowired
    private ILikeCommentRepository iLikeCommentRepository;

    @Override
    public Iterable<LikeComment> findAll() {
        return iLikeCommentRepository.findAll();
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
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

    @Override
    public void deleteAllByComment(Comment comment) {
        iLikeCommentRepository.deleteAllByComment(comment);
    }
}
