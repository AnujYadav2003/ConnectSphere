package com.example.Connectify.Controller;

import com.example.Connectify.Service.MessageService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.model.Message;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MessageController {
@Autowired
    private MessageService messageService;
@Autowired
    private UserService userService;
@PostMapping("/api/messages/chat/{chatId}")
public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization")String jwt,
           @PathVariable Long chatId) throws Exception{
User user =userService.findUserByJwt(jwt);
return messageService.createMessage(user,chatId,req);
}

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessages(@RequestHeader("Authorization")String jwt,
                                 @PathVariable Long chatId) throws Exception{
        User user =userService.findUserByJwt(jwt);
        List<Message> messages=messageService.findChatsMessages(chatId);
        return messages;
    }
}
