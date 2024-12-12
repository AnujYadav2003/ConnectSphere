package com.example.Connectify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(name="caption",nullable = false)
    @JsonProperty("caption")
    private String caption;

    @Column(name = "imageurl")
    @JsonProperty("imageurl")
    private String imageurl;

    @Column(name = "videourl")
    @JsonProperty("videourl")
   private String videourl;

    @Column(name="createdAt")
    private LocalDateTime createdAt;


//    one user --> many posts
  @ManyToOne
    private User user;
//   one user--->many post ko like kar skta hai
@OneToMany
    private List<User>liked =new ArrayList<>();
@OneToMany
  private List<Comment>comments=new ArrayList<>();

}
