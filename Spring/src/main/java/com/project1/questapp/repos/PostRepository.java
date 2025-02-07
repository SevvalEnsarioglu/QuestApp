package com.project1.questapp.repos;

import com.project1.questapp.entities.Post;
import com.project1.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(User userId);

}
