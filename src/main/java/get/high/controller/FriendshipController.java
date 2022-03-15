package get.high.controller;

import get.high.model.entity.Friendship;
import get.high.service.IFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/friendship")
public class FriendshipController {

    @Autowired
    private IFriendshipService friendshipService;

    @GetMapping
    public ResponseEntity<Iterable<Friendship>> showAll() {
        Iterable<Friendship> friendships = friendshipService.findAll();

        if (!friendships.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(friendships, HttpStatus.OK);
        }
    }

//    @PostMapping("/{from_user_id}/{to_user_id}")
//    public ResponseEntity<Friendship> addFriend(@PathVariable("from_user_id") long from_user_id, @PathVariable("to_user_id") long to_user_id) {
//
//    }
}
