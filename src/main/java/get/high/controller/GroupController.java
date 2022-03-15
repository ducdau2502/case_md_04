package get.high.controller;

import get.high.model.entity.GroupMember;
import get.high.model.entity.Groups;
import get.high.model.entity.Post;
import get.high.model.entity.UserInfo;
import get.high.service.IGroupMemberService;
import get.high.service.IGroupService;
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

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/group")
public class GroupController {
    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupMemberService groupMemberService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @GetMapping("/get-group-member")
    public ResponseEntity<Iterable<GroupMember>> getAllGroupMember() {
        Iterable<GroupMember> groupMembers = groupMemberService.findAll();
        if (!groupMembers.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupMembers, HttpStatus.OK);
    }

    @GetMapping("/get-group")
    public ResponseEntity<Iterable<Groups>> getAllGroups() {
        Iterable<Groups> groupsList = groupService.findAll();
        if (!groupsList.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }

    //tạo group
    @PostMapping("/create-group/{userinfo_id}")
    public ResponseEntity<Groups> createGroup(@PathVariable("userinfo_id") Long userinfo_id, @RequestBody Groups groups) {
        Groups groupsCreate = groupService.save(groups);
        Optional<UserInfo> userInfo = userService.findById(userinfo_id);
        GroupMember groupMember = new GroupMember(0, userInfo.get(), groups);
        groupMemberService.save(groupMember);
        return new ResponseEntity<>(groupsCreate, HttpStatus.CREATED);
    }

    //tạo post trong group + upload-File
    @PostMapping("/create-post/{groups_id}/{userinfo_id}")
    public ResponseEntity<Post> createPost(@PathVariable("groups_id") Long groups_id, @PathVariable("userinfo_id") Long userinfo_id,
                                           @RequestPart Post post, @RequestPart("file") MultipartFile file) {
        post.setDateCreated(LocalDate.now());
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImgUrl(view + fileName);
        post.setGroups(groupService.findById(groups_id).get());
        post.setUserInfo(userService.findById(userinfo_id).get());
        Post postCreate = postService.save(post);
        return new ResponseEntity<>(postCreate, HttpStatus.CREATED);
    }

    @GetMapping("/search-group")
    public ResponseEntity<Iterable<Groups>> findAllByName(@RequestParam Optional<String> search) {
        Iterable<Groups> groupsList = groupService.findAllByName(search.get());
        if (!groupsList.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }
}
