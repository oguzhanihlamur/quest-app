package dev.antozy.questapp.repositories;

import dev.antozy.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);

    List<Like> findAllByUserId(Optional<Long> userId);

    List<Like> findAllByPostId(Optional<Long> postId);
}
