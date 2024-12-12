


package com.example.Connectify.Controller;

import com.example.Connectify.Service.ChatService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.Chat;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(
            @RequestHeader("Authorization") String jwt,
            @RequestBody ChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.getUserById(req.getUserId());
        return chatService.createChat(reqUser, user2);
    }

        @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization")String jwt) throws Exception {
    User user=userService.findUserByJwt(jwt);
        List<Chat> chat = chatService.findUsersChat(user.getId());
        return chat;
    }
}
