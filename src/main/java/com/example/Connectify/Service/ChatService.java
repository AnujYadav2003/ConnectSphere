//package com.example.Connectify.Service;
//
//import com.example.Connectify.Repository.ChatRepository;
//import com.example.Connectify.model.Chat;
//import com.example.Connectify.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ChatService {
//@Autowired
//private UserService userService;
//@Autowired
//private ChatRepository chatRepository;
//    public Chat createChat(User reqUser, User user2) throws Exception
//    {
//         Chat isExists=chatRepository.findChatByUsers(reqUser,user2);
//   if(isExists!=null)
//       return isExists;
//
//   Chat chat=new Chat();
//   chat.getUsers().add(user2);
//   chat.getUsers().add(reqUser);
//   chat.setTimestamp(LocalDateTime.now());
//   return chatRepository.save(chat);
//    }
//
//    public Chat findChatById(Long chatId)
//    {
//      Optional<Chat> opt=chatRepository.findById(chatId);
//       if(opt.isEmpty())
//           throw new RuntimeException("Chat not found with id - "+ chatId);
//       return opt.get();
//    }
//
//    public List<Chat> findUsersChat(Long userId)
//    {
//        return  chatRepository.findByUsersId(userId);
//    }
//}

package com.example.Connectify.Service;

import com.example.Connectify.Repository.ChatRepository;
import com.example.Connectify.model.Chat;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public Chat createChat(User reqUser, User user2) throws Exception {
        // Use the User objects directly in the repository method
        Chat existingChat = chatRepository.findChatByUserId(user2, reqUser);
        if (existingChat != null) {
            return existingChat;
        }

        // Create a new chat if it doesn't exist
        Chat chat = new Chat();
        chat.getUsers().add(reqUser);
        chat.getUsers().add(user2);
        chat.setTimestamp(LocalDateTime.now());

        // Save the chat to the repository
        return chatRepository.save(chat);
    }

        public Chat findChatById(Long chatId)
    {
      Optional<Chat> opt=chatRepository.findById(chatId);
       if(opt.isEmpty())
           throw new RuntimeException("Chat not found with id - "+ chatId);
       return opt.get();
    }

    public List<Chat> findUsersChat(Long userId)
    {
        return  chatRepository.findByUsersId(userId);
    }
}