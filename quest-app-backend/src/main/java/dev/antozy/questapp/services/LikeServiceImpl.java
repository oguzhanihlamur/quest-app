package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Like;
import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.entities.User;
import dev.antozy.questapp.repositories.LikeRepository;
import dev.antozy.questapp.repositories.PostRepository;
import dev.antozy.questapp.repositories.UserRepository;
import dev.antozy.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Like> getLikes(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findAllByUserIdAndPostId(userId, postId);
        } else if (userId.isPresent()) {
            return likeRepository.findAllByUserId(userId);
        } else if (postId.isPresent()) {
            return likeRepository.findAllByPostId(postId);
        } else {
            return likeRepository.findAll();
        }
    }

    @Override
    public Like saveLike(LikeCreateRequest likeCreateRequest) {
        if (likeCreateRequest == null) {
            return null;
        } else {
            User user = userRepository.findById(likeCreateRequest.getUserId()).orElse(null);
            Post post = postRepository.findById(likeCreateRequest.getPostId()).orElse(null);
            if (user != null && post != null) {
                Like like = new Like();
                like.setId(likeCreateRequest.getId());
                like.setUser(user);
                like.setPost(post);
                return likeRepository.save(like);
            } else {
                return null;
            }
        }
    }

    @Override
    public Like getLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
