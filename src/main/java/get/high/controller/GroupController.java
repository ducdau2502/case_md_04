package get.high.controller;

import get.high.model.entity.Groups;
import get.high.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @GetMapping("search-group")
    public ResponseEntity<Iterable<Groups>> findAllByName(@RequestParam Optional<String> name) {
        Iterable<Groups> groupsList = groupService.findAllByName(name.get());
        if (!groupsList.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groupsList, HttpStatus.OK);
    }

    @PostMapping("create-group")
    public ResponseEntity<Groups> saveGroup(@RequestBody Groups groups) {
        return new ResponseEntity<>(groupService.save(groups), HttpStatus.CREATED);
    }
}
