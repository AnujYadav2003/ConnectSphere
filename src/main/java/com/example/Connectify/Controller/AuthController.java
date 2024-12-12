//package com.example.Connectify.Controller;
//
//import com.example.Connectify.Repository.UserRepository;
//import com.example.Connectify.Service.UserService;
//import com.example.Connectify.config.JwtProvider;
//import com.example.Connectify.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    @Autowired
//   private UserService userService;
//    @Autowired
//    private  PasswordEncoder passwordEncoder;
//    @Autowired
//    private UserRepository userRepository;
//    @PostMapping("/signup")
//    public AuthenticatorResponse registerUser(@RequestBody User user) {
//
//        User isExist=userRepository.findByEmail(user.getEmail());
//        if(isExist!=null){
//            throw new Exception("Email already exists");
//        }
//        User newUser = new User();
//        newUser.setEmail(user.getEmail());
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//
////        return userRepository.save(newUser);
//        User savedUser=userRepository.save(newUser);
//        Authentication authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
//        String token= JwtProvider.generateToken(authentication);
//        AuthResponse res=new AuthResponse(token,"Registered Successfully");
//        return res;
//    }
//}


package com.example.Connectify.Controller;

import com.example.Connectify.Repository.UserRepository;
import com.example.Connectify.Service.CustomerUserDetailsService;
import com.example.Connectify.Service.UserService;
import com.example.Connectify.config.JwtProvider;
import com.example.Connectify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  CustomerUserDetailsService customerUserDetailsService;
    @PostMapping("/signup")
    public AuthResponse registerUser(@RequestBody User user) throws Exception {
        // Handling Optional<User> from findByEmail
        Optional<User> isExist = userRepository.findByEmail(user.getEmail());
        if (isExist.isPresent()) {
            throw new Exception("Email already exists");
        }

        // Create a new user
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());

        // Save the user and generate a token
        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);

        // Return the response
        return new AuthResponse(token, "Registered Successfully");
    }
//auth/signin
@PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        // Return the response
        return new AuthResponse(token, "Login Successfully");
    }
    private Authentication authenticate(String email,String password){
        UserDetails userDetails=customerUserDetailsService.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password not match");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
