package get.high.service.impl;

import get.high.model.entity.Comment;
import get.high.model.entity.UserInfo;
import get.high.repository.ICommentRepository;
import get.high.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository iCommentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public UserInfo findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        iCommentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllByPost_Id(Long id) {
        return iCommentRepository.findAllByPost_Id(id);
    }
}
