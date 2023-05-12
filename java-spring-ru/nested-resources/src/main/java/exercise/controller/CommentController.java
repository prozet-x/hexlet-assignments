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
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(() -> new ResourceNotFoundException("Very very bad"));
    }

    @PostMapping("/{postId}/comments")
    public Comment createComment(@RequestBody Comment comment, @PathVariable long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Very very bad"));
        comment.setPost(post);;
        return commentRepository.save(comment);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public Comment updateComment(@RequestBody Comment newComment, @PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(() -> new ResourceNotFoundException("Very very bad"));
        comment.setContent(newComment.getContent());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(() -> new ResourceNotFoundException("Very very bad"));
        commentRepository.delete(comment);
    }
    // END
}
