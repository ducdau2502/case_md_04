package get.high.controller;

import get.high.model.entity.Post;
import get.high.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAll() {
        Iterable<Post> posts = postService.findAll();
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/search-hasTag")
    public ResponseEntity<Iterable<Post>> findAllByHasTag(@RequestParam Optional<String> hasTag) {
        Iterable<Post> posts = postService.findAllByHasTag(hasTag.get());
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> detail(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Post> add(@RequestBody Post post) {
        Post postCreate = postService.save(post);
        return new ResponseEntity<>(postCreate, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam("name") String name) {
        System.out.println(file.getOriginalFilename());
        System.out.println(name);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Post> update(@RequestBody Post postUpdate, @PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postUpdate.setId(post.get().getId());
        postUpdate = postService.save(postUpdate);
        return new ResponseEntity<>(postUpdate, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Post> delete(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }
}
