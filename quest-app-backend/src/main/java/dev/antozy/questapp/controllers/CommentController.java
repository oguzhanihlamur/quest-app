package dev.antozy.questapp.controllers;

import dev.antozy.questapp.entities.Comment;
import dev.antozy.questapp.requests.CommentCreateRequest;
import dev.antozy.questapp.requests.CommentUpdateRequest;
import dev.antozy.questapp.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        log.info("in getAllComments method.");
        return commentService.getAllComments(userId, postId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        log.info("in createComment method.");
        return commentService.saveComment(commentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        log.info("in getComment method.");
        return commentService.getComment(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        log.info("in updateComment method.");
        return commentService.updateComment(commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        log.info("in deleteComment method.");
        commentService.deleteComment(commentId);
    }
}
