package com.example.Connectify.Controller;

import com.example.Connectify.Service.ReelsService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.Reels;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReelsService reelsService;


    @PostMapping("/api/reels")
    public Reels createReels(@RequestHeader("Authorization")String jwt,
                @RequestBody Reels reels )
    {
        User user=userService.findUserByJwt(jwt);

        if(user==null)
        {
            throw new RuntimeException("User not existing such token");
        }
        return  reelsService.createReels(reels,user);

    }

    @GetMapping("/api/reels")
    public List<Reels> getAllReels()
    {
        return reelsService.getAllReels();
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> getUserReels(@PathVariable Long userId) throws Exception
    {

        return  reelsService.getUserReels(userId);
    }
}
