package dev.antozy.questapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    String text;
    Long postId;
    Long userId;
}
