package com.project1.questapp.services;

import com.project1.questapp.entities.Post;
import com.project1.questapp.entities.User;
import com.project1.questapp.repos.PostRepository;
import com.project1.questapp.repos.UserRepository;
import com.project1.questapp.requests.PostCreateRequest;
import com.project1.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // Artık statik değil
    public List<Post> getAllPost(Optional<Long> userId) {
        if (userId.isPresent()) {
            Optional<User> user = userRepository.findById(userId.get());

            // Kullanıcı bulunamadığında boş liste döndür
            if (user.isEmpty()) {
                return Collections.emptyList();  // Daha güvenli boş liste
            }

            return postRepository.findByUserId(user.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        // Kullanıcıyı getir
        User user = userService.getOneUserById(newPostRequest.getUserId());

        if(user == null)
            return null;

        // Post objesi oluştur
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setText(newPostRequest.getText());
        toSave.setUserId(user);  // userId yerine User objesi ayarlanıyor

        // Post'u kaydet
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        else{
            return null;
        }
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
