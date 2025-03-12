package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.entity.Discussion;
import net.enjoy.springboot.registrationlogin.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discussions")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Allows frontend requests
public class DiscussionController {

    private final DiscussionService discussionService;

    @Autowired
    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    // ✅ Save Discussion with Questions
    @PostMapping("/save")
    public ResponseEntity<?> saveDiscussion(@RequestBody Discussion discussion) {
        try {
            Discussion savedDiscussion = discussionService.saveDiscussion(discussion);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "Discussion saved successfully!",
                    "uniqueCode", savedDiscussion.getUniqueCode()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error saving discussion: " + e.getMessage()));
        }
    }

    // ✅ Get All Discussions
    @GetMapping("/all")
    public ResponseEntity<List<Discussion>> getAllDiscussions() {
        List<Discussion> discussions = discussionService.getAllDiscussions();
        return ResponseEntity.ok(discussions);
    }
}
