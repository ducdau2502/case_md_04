package get.high.service.impl;

import get.high.model.entity.Comment;
import get.high.model.entity.Post;
import get.high.model.entity.UserInfo;
import get.high.repository.IPostRepository;
import get.high.service.ICommentService;
import get.high.service.ILikePostService;
import get.high.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository iPostRepository;

    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private ILikePostService iLikePostService;

    @Override
    public Iterable<Post> findAll() {
        return iPostRepository.findAll();
    }

    @Override
    public UserInfo findById(Long id) {
        return iPostRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return iPostRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        Iterable<Comment> comments = iCommentService.findAllByPost_Id(id);
        for (Comment comment : comments) {
            iCommentService.remove(comment.getId());
        }
        iLikePostService.deleteAllByPost_Id(id);
        iPostRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByHasTag(String hasTag) {
        return iPostRepository.findAllByContentContaining(hasTag);
    }
}
