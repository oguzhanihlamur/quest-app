package dev.antozy.questapp.services;

import dev.antozy.questapp.entities.Comment;
import dev.antozy.questapp.entities.Post;
import dev.antozy.questapp.entities.User;
import dev.antozy.questapp.repositories.CommentRepository;
import dev.antozy.questapp.repositories.PostRepository;
import dev.antozy.questapp.repositories.UserRepository;
import dev.antozy.questapp.requests.CommentCreateRequest;
import dev.antozy.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findAllByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    @Override
    public Comment saveComment(CommentCreateRequest commentCreateRequest) {
        User user = userRepository.findById(commentCreateRequest.getUserId()).orElse(null);
        Post post = postRepository.findById(commentCreateRequest.getPostId()).orElse(null);
        if (user != null && post != null) {
            Comment comment = new Comment();
            comment.setId(commentCreateRequest.getId());
            comment.setText(commentCreateRequest.getText());
            comment.setUser(user);
            comment.setPost(post);
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = new Comment();
            commentToUpdate.setText(commentUpdateRequest.getText());
            commentToUpdate.setId(commentId);
            commentToUpdate.setUser(comment.get().getUser());
            commentToUpdate.setPost(comment.get().getPost());
            return commentRepository.save(commentToUpdate);
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
