package com.example.Connectify.Repository;

import com.example.Connectify.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Long>  {
    public List<Reels> findByUserId(Long userId) throws Exception;
}
