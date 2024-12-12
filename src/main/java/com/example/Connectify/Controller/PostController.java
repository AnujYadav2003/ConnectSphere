package com.example.Connectify.Controller;

import com.example.Connectify.Service.PostService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.Post;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;
@Autowired
 private UserService userService;
//    @GetMapping("/")
//    public String welcomePost() {
//        return "Welcome to the Post Service!";
//    }

//    @PostMapping("/create/{userId}")
//    public Post createPost(@RequestBody Post post, @PathVariable Long userId) {
//        return postService.createPost(post, userId);
//    }

    @GetMapping("/getposts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/getpostbyid/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/getpostsbyuserid/{userId}")
    public List<Post> findPostByUserId(@PathVariable Long userId) {
        return postService.findPostByUserId(userId);
    }

    @PutMapping("/updatepostbyid/{id}")
    public Post updatePostById(@RequestBody Post post, @PathVariable Long id) {
        return postService.updatePostById(post, id);
    }

    @PutMapping("/savedpost/{postId}/")
    public Post savedPost(@PathVariable Long postId, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        System.out.println("dfghjfdgf");
        return postService.savedPost(postId, reqUser.getId());
    }

    @PutMapping("/likepost/{postId}")
    public Post likePost(@PathVariable Long postId,
                         @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        return postService.likePost(postId, reqUser.getId());
    }

//    @DeleteMapping("/deletepost/{postId}")
//    public String deletePostById(@RequestHeader("Authorization") String jwt,
//           @PathVariable Long postId) {
//        User reqUser = userService.findUserByJwt(jwt);
//         postService.deletePostById(postId,reqUser.getId());
//         return "Deleted successfully";
//    }


    @DeleteMapping("/deletepost/{postId}")
    public String deletePost(@RequestHeader("Authorization") String jwt,
                                 @PathVariable Long postId) {
        User reqUser = userService.findUserByJwt(jwt);
        if (reqUser == null) {
            throw new RuntimeException("Invalid token. User not found.");
        }
        return postService.deletePost(postId, reqUser.getId());
    }




    @PostMapping("/api/post")
    public Post createPost(@RequestHeader("Authorization") String jwt,
                           @RequestBody Post post) {
        // Log the token for debugging
        System.out.println("JWT Token: " + jwt);
        User user = userService.findUserByJwt(jwt);
        System.out.println(user); // Log user object for debugging

        if (user == null) {
            throw new RuntimeException("User not found from JWT");
        }

        return postService.createPost(post, user.getId());
    }


}
