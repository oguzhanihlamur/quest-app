package dev.antozy.questapp.controllers;

import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.requests.PostCreateRequest;
import dev.antozy.questapp.requests.PostUpdateRequest;
import dev.antozy.questapp.responses.PostResponse;
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
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.findAllPosts(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostCreateRequest) {
        return postService.savePost(newPostCreateRequest);
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
