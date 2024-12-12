package com.example.Connectify.Repository;

import com.example.Connectify.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment,Long>{
}
