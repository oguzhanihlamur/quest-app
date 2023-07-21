package dev.antozy.questapp.repositories;

import dev.antozy.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
