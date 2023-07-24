package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Comment;
import dev.antozy.questapp.entities.Like;
import dev.antozy.questapp.requests.LikeCreateRequest;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<Like> getLikes(Optional<Long> userId, Optional<Long> postId);

    Like saveLike(LikeCreateRequest likeCreateRequest);

    Like getLike(Long likeId);

    void deleteLike(Long likeId);
}
