package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.requests.PostCreateRequest;
import dev.antozy.questapp.requests.PostUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAllPosts(Optional<Long> userId);

    Post findPostById(Long postId);

    Post savePost(PostCreateRequest newPostCreateRequest);

    Post updatePostById(Long postId, PostUpdateRequest postUpdateRequest);

    void deletePostById(Long postId);
}