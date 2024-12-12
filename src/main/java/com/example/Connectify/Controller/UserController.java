package com.example.Connectify.Controller;

import com.example.Connectify.Repository.UserRepository;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String solve() {
        return "Welcome to the User Service!";
    }

//    @PostMapping("/create")
//    public User registerUser(@RequestBody User user) {
//        return userService.registerUser(user);
//    }
    @GetMapping("/api/getusers")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

//    @GetMapping("/getbyid/{id}")
//    public User getUserById(@PathVariable Long id)
//    {
//        return userService.getUserById(id);
//    }

    @GetMapping("/getbyemail/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email)
    {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id)
    {
        userService.deleteUserById(id);
        return "Deleted successfully";
    }
//    @PostMapping("/updatebyid/{id}")
//    public String updateUserInfo(@RequestBody User user, @PathVariable Long id) {
//        try {
//            userService.updateUser(user, id);
//            return "Updated successfully";
//        } catch (RuntimeException e) {
//            return e.getMessage();
//        }
//    }
//    @PutMapping("/followuser/user1/{id1}/user2/{id2}")
//    public String followUser(@PathVariable Long id1, @PathVariable Long id2) {
//        try {
//            userService.followUser(id1, id2);
//            return "Followed successfully";
//        } catch (RuntimeException e) {
//            return e.getMessage();
//        }
//    }

    @GetMapping("/searchuser")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
    }


//    --------------------

  //update users
    @PutMapping("/api/users/update")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) {
        User reqUser = userService.findUserByJwt(jwt); // Find the user making the request
        User updatedUser = userService.updateUser(user, reqUser.getId()); // Update the user
        return updatedUser;
    }

////follow users-
//@PutMapping("/api/users/follow/{userId2}")
//public User followUser(@RequestHeader("Authorization")String jwt,@PathVariable Long id2)throws Exception {
//    User reqUser=userService.findUserByJwt(jwt);
//    System.out.println(reqUser.getId());
//    User user=userService.followUser(reqUser.getId(),id2);
//    return user;
//}


    @PutMapping("/api/users/follow/{id2}")
    public User followUser(@RequestHeader("Authorization") String jwt, @PathVariable Long id2) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        System.out.println(reqUser.getId());
        User user = userService.followUser(reqUser.getId(), id2);
        return user;
    }

//get user info
    @GetMapping("/api/users/profile")
    public User findUserByJwt(@RequestHeader ("Authorization")String jwt)
    {
        User user=userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;

    }
}
