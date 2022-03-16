package get.high.controller;

import get.high.model.entity.Post;
import get.high.service.IPostService;
import get.high.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/post")
public class PostController {
    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

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

    // detail post
    @GetMapping("/{id}")
    public ResponseEntity<Post> detail(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //tạo post + upload file
    @PostMapping("/{userinfo_id}")
    public ResponseEntity<Post> add(@PathVariable("userinfo_id") Long userinfo_id,
                                    @RequestPart("post") Post post, @RequestPart("file") MultipartFile file) {
        post.setDateCreated(LocalDate.now());
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImgUrl(view + fileName);
        post.setUserInfo(userService.findById(userinfo_id).get());
        Post postCreate = postService.save(post);
        return new ResponseEntity<>(postCreate, HttpStatus.CREATED);
    }

    //sửa post + upload file
    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable("id") Long id, @RequestPart("postUpdate") Post postUpdate,
                                            @RequestPart("file") MultipartFile file) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postUpdate.setId(post.get().getId());
        if (file.getSize() != 0) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            postUpdate.setImgUrl(view + fileName);
        } else {
            postUpdate.setImgUrl(post.get().getImgUrl());
        }
        postUpdate = postService.save(postUpdate);
        return new ResponseEntity<>(postUpdate, HttpStatus.OK);
    }

    //xóa post
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }
}
