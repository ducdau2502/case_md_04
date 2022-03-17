package get.high.controller;

import get.high.model.entity.UserInfo;
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
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @Autowired
    private IUserService iUserService;

    @GetMapping
    public ResponseEntity<Iterable<UserInfo>> findAll() {
        Iterable<UserInfo> userInfos = iUserService.findAll();
        if (!userInfos.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<UserInfo>> findAllByFullNameContaining(@RequestParam Optional<String> fullName) {
        Iterable<UserInfo> userInfos = iUserService.findAllByFullNameContaining(fullName.get());
        if (!userInfos.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userInfos, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> profile(@PathVariable("id") Long id) {
        Optional<UserInfo> userInfo = iUserService.findById(id);
        return userInfo.map(info -> new ResponseEntity<>(info, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}")
    public  ResponseEntity<UserInfo> uploadFile(@PathVariable("id") Long id, @RequestPart("file") MultipartFile file){
        Optional<UserInfo>  userInfo = iUserService.findById(id);
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
        userInfo.get().setAvatarUrl(view + fileName);
        iUserService.save(userInfo.get());
        return new ResponseEntity<>(userInfo.get(),HttpStatus.OK);
    }


}
