package dev.antozy.questapp.controllers;

import dev.antozy.questapp.entities.Comment;
import dev.antozy.questapp.entities.Like;
import dev.antozy.questapp.requests.LikeCreateRequest;
import dev.antozy.questapp.responses.LikeResponse;
import dev.antozy.questapp.services.LikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        log.info("in getLikes method.");
        return likeService.getLikes(userId, postId);
    }

    @PostMapping
    public Like saveLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        log.info("in saveLike method.");
        return likeService.saveLike(likeCreateRequest);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId) {
        log.info("in getLike method.");
        return likeService.getLike(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        log.info("in deleteLike method.");
        likeService.deleteLike(likeId);
    }
}
