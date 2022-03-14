package get.high.controller;

import get.high.model.entity.GroupMember;
import get.high.model.entity.Groups;
import get.high.service.IGroupMemberService;
import get.high.service.IGroupService;
import get.high.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupMemberService groupMemberService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<GroupMember>> getAll() {
        Iterable<GroupMember> groupMembers = groupMemberService.findAll();
        if (!groupMembers.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupMembers, HttpStatus.OK);
    }

    @PostMapping("/create-group/{userinfo_id}")
    public ResponseEntity<Groups> createGroup(@PathVariable("userinfo_id") Long userinfo_id, @RequestBody Groups groups) {
        Groups groupsCreate = groupService.save(groups);
        GroupMember groupMember = new GroupMember(0, userService.findById(userinfo_id), groups);
        groupMemberService.save(groupMember);
        return new ResponseEntity<>(groupsCreate, HttpStatus.CREATED);
    }
}
