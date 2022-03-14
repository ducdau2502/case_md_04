package get.high.controller;

import get.high.model.entity.LikePost;
import get.high.model.entity.Post;
import get.high.model.entity.UserInfo;
import get.high.service.ILikePostService;
import get.high.service.IPostService;
import get.high.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/likePost")
public class LikePostController {

    @Autowired
    private ILikePostService likePostService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @PostMapping("/{post_id}/{userinfo_id}")
    public ResponseEntity<LikePost> checkLikeComment(@PathVariable("post_id") long post_id, @PathVariable("userinfo_id") long userinfo_id) {
        Optional<LikePost> likePostOptional = likePostService.findByPost_IdAndUserInfo_Id(post_id, userinfo_id);
        if (!likePostOptional.isPresent()) {
            UserInfo userInfo = userService.findById(userinfo_id).get();
            Post post = postService.findById(post_id).get();
            LikePost likePost = new LikePost(post, userInfo);
            likePostService.save(likePost);
        } else {
            likePostService.remove(likePostOptional.get().getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> countLikePostByPost_Id(@PathVariable("id") long id) {
        Long countLikePostByPost_Id = likePostService.countLikeCommentByPost_Id(id);
        return new ResponseEntity<>(countLikePostByPost_Id, HttpStatus.OK);
    }
}
