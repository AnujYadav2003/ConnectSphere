package com.example.Connectify.Controller;

import com.example.Connectify.Service.StoryService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.Story;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story,
      @RequestHeader("Authorization")String jwt){
        User user=userService.findUserByJwt(jwt);
        return storyService.createStory(story,user);
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story>findStoryByUserId(@PathVariable Long userId,
         @RequestHeader("Authorization")String jwt) throws Exception{
        User reqUser=userService.findUserByJwt(jwt);
        List<Story> stories=storyService.findStoryByUserId(userId);
        return stories;
    }

}
