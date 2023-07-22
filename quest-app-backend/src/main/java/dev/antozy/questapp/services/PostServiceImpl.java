package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.entities.User;
import dev.antozy.questapp.repositories.PostRepository;
import dev.antozy.questapp.requests.PostCreateRequest;
import dev.antozy.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<Post> findAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post savePost(PostCreateRequest newPostCreateRequest) {
        User user = userService.findUserById(newPostCreateRequest.getUserId());
        if(user == null){
            return null;
        } else {
            Post post = new Post();
            post.setId(newPostCreateRequest.getId());
            post.setUser(user);
            post.setTitle(newPostCreateRequest.getTitle());
            post.setText(newPostCreateRequest.getText());

            return postRepository.save(post);
        }
    }

    @Override
    public Post updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post postToUpdate = new Post();
            postToUpdate.setId(postId);
            postToUpdate.setTitle(postUpdateRequest.getTitle());
            postToUpdate.setText(postUpdateRequest.getText());
            postToUpdate.setUser(post.get().getUser());
            return postRepository.save(postToUpdate);
        }
        return null;
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
