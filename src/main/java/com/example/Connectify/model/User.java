package com.example.Connectify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    @JsonProperty("firstname")
    private String firstName;


    @Column(name = "lastname", nullable = false)
    @JsonProperty("lastname")
    private String lastName;


    @JsonProperty("email")
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    private String password;


    private List<Long> followers= new ArrayList<>();
    private List<Long> followings=new ArrayList<>();
     @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

}
