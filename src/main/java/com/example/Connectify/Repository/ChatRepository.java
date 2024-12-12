//package com.example.Connectify.Repository;
//
//import com.example.Connectify.model.Chat;
//import com.example.Connectify.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ChatRepository extends JpaRepository<Chat,Long> {
//
//    public List<Chat> findByUsersId(Long userId);
//
//    @Query("select c from Chat c Where :user1 Member of c.users And : user2 Member of c.users")
//     public Chat findChatByUsers(@Param("user1")User user,@Param("user2") User user2);
//}


package com.example.Connectify.Repository;

import com.example.Connectify.model.Chat;
import com.example.Connectify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {


    public List<Chat> findByUsersId(Long userId);


    // Custom query to find a chat between two users (using User objects)
    @Query("SELECT c FROM Chat c WHERE :user1 MEMBER OF c.users AND :user2 MEMBER OF c.users")
    Chat findChatByUserId(User user1, User user2);

}
