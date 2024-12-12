package com.example.Connectify.Controller;

import com.example.Connectify.Service.CommentService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.Comment;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
@Autowired
    private CommentService commentService;
@Autowired
private UserService userService;

@PostMapping("/api/comments/post/{postId}")
   public Comment createComment(@RequestBody Comment comment,
                                @RequestHeader("Authorization")String jwt, @PathVariable Long postId) throws Exception
   {
       User user=userService.findUserByJwt(jwt);
       Comment createdComment =commentService.createComment(comment,postId,user.getId());
       return createdComment;
   }

    @PutMapping("/api/like/post/{commentId}")
    public Comment likeComment(
                                 @RequestHeader("Authorization")String jwt,
                                  @PathVariable Long commentId)
    {
        User user=userService.findUserByJwt(jwt);
        Comment likedComment =commentService.likeComment(commentId,user.getId());
        return likedComment;
    }

    @PutMapping("/findcommentbyid/{commentId}")
    public Comment findCommentById(@PathVariable Long commentId)
    {
        Comment com=commentService.findCommentById(commentId);
        return com;
    }
}
