package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Like;
import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.entities.User;
import dev.antozy.questapp.repositories.LikeRepository;
import dev.antozy.questapp.repositories.PostRepository;
import dev.antozy.questapp.repositories.UserRepository;
import dev.antozy.questapp.requests.LikeCreateRequest;
import dev.antozy.questapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<LikeResponse> getLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likeList = null;
        if (userId.isPresent() && postId.isPresent()) {
            likeList = likeRepository.findAllByUserIdAndPostId(userId, postId);
        } else if (userId.isPresent()) {
            likeList = likeRepository.findAllByUserId(userId);
        } else if (postId.isPresent()) {
            likeList = likeRepository.findAllByPostId(postId);
        } else {
            likeList = likeRepository.findAll();
        }
        return likeList.stream().map(LikeResponse::new).collect(Collectors.toList());
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
