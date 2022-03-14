package get.high.controller;

import get.high.model.entity.Post;
import get.high.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping("/search-hasTag")
    public ResponseEntity<Iterable<Post>> findAllByHasTag(@RequestParam Optional<String> hasTag) {
        Iterable<Post> posts = postService.findAllByHasTag(hasTag.get());
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
