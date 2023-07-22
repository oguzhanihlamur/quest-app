package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Comment;
import dev.antozy.questapp.requests.CommentCreateRequest;
import dev.antozy.questapp.requests.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId);

    Comment saveComment(CommentCreateRequest commentCreateRequest);

    Comment getComment(Long commentId);

    Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest);

    void deleteComment(Long commentId);
}
