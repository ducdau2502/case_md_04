package get.high.controller;

import get.high.model.entity.GroupMember;
import get.high.model.entity.Groups;
import get.high.model.entity.UserInfo;
import get.high.service.IGroupMemberService;
import get.high.service.IGroupService;
import get.high.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/group-member")
public class GroupMemberController {
    @Autowired
    private IGroupMemberService groupMemberService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @GetMapping
    public ResponseEntity<Iterable<GroupMember>> getAllGroupMember() {
        Iterable<GroupMember> groupMembers = groupMemberService.findAll();
        if (!groupMembers.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupMembers, HttpStatus.OK);
    }

    @PostMapping("/send-request/{userinfo_id}/{groups_id}")
    public ResponseEntity<GroupMember> sendRequest(@PathVariable("userinfo_id") Long userinfo_id,
                                                   @PathVariable("groups_id") Long groups_id) {
        Optional<UserInfo> userInfo = userService.findById(userinfo_id);
        Optional<Groups> groups = groupService.findById(groups_id);
        GroupMember groupMember = new GroupMember(2, userInfo.get(), groups.get());
        return new ResponseEntity<>(groupMemberService.save(groupMember), HttpStatus.CREATED);
    }

    @PutMapping("/join-group/{userinfo_id}/{groups_id}")
    public ResponseEntity<GroupMember> joinGroup(@PathVariable("userinfo_id") Long userinfo_id,
                                                   @PathVariable("groups_id") Long groups_id) {
        Optional<GroupMember> groupMember = groupMemberService.findByGroups_IdAndUserInfo_Id(groups_id, userinfo_id);
        groupMember.get().setStatus(1);
        groupMemberService.save(groupMember.get());
        return new ResponseEntity<>(groupMember.get(), HttpStatus.OK);
    }

    @DeleteMapping("/out-group/{userinfo_id}/{groups_id}")
    public ResponseEntity<GroupMember> outGroup(@PathVariable("userinfo_id") Long userinfo_id,
                                                 @PathVariable("groups_id") Long groups_id) {
        Optional<GroupMember> groupMember = groupMemberService.findByGroups_IdAndUserInfo_Id(groups_id, userinfo_id);
        groupMemberService.remove(groupMember.get().getId());
        return new ResponseEntity<>(groupMember.get(), HttpStatus.OK);
    }
}
