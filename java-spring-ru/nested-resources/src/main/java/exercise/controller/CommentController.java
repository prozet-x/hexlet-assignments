package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getAllPostComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getAllPostComments(@PathVariable long postId, @PathVariable long commentId) {
        Comment result = commentRepository.findByPostAndId(postRepository.findById(postId).get(), commentId);
        if (result == null) {
            throw new ResourceNotFoundException("Very very bad");
        }
        return result;
    }

    @PostMapping("/{postId}/comments")
    public Comment getAllPostComments(@RequestBody Comment comment, @PathVariable long postId) {
        if (postRepository.existsById(postId)) {
            comment.setPost(postRepository.findById(postId).get());
            return commentRepository.save(comment);
        }
        throw new ResourceNotFoundException("Post not found");
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public Comment updatePostComment(@RequestBody Comment comment, @PathVariable long postId, @PathVariable long commentId) {
        if (postRepository.existsById(postId) && commentRepository.existsById(commentId)) {
            comment.setId(commentId);
            comment.setPost(postRepository.findById(postId).get());
            return commentRepository.save(comment);
        }
        throw new ResourceNotFoundException("Post or comment not found");
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findByPostAndId(postRepository.findById(postId).get(), commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("Very very bad");
        }
        commentRepository.deleteById(commentId);
    }
    // END
}
