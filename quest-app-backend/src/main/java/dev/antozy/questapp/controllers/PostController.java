package dev.antozy.questapp.controllers;

import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.reqıests.PostCreateRequest;
import dev.antozy.questapp.reqıests.PostUpdateRequest;
import dev.antozy.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.findAllPosts(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostCreateRequest) {
        return postService.saveOnePost(newPostCreateRequest);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        //Custom exception
        return postService.findPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updatePostById(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);
    }
}
