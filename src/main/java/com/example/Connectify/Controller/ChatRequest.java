//package com.example.Connectify.Controller;
//
//import com.example.Connectify.Service.ChatService;
//import com.example.Connectify.model.Chat;
//import com.example.Connectify.model.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class ChatRequest {
//   private Long userId;
//}


package com.example.Connectify.Controller;

public class ChatRequest {
   private Long userId; // The ID of the second user to create a chat with

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }
}
