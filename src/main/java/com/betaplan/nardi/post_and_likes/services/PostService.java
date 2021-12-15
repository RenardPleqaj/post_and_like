package com.betaplan.nardi.post_and_likes.services;

import com.betaplan.nardi.post_and_likes.models.Like;
import com.betaplan.nardi.post_and_likes.models.Post;
import com.betaplan.nardi.post_and_likes.repositories.LikeRepository;
import com.betaplan.nardi.post_and_likes.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeRepository likeRepository;

    public Post createUpdatePost(Post post){
        return postRepository.save(post);
    }
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }
    public List<Post> findAllPosts(){
        return (List<Post>) postRepository.findAll();
    }
    public List<Post> findAllMyPosts(Long userId){
        return postRepository.findAllByUserId(userId);
    }
    public Post findPost(Long postId){
        return postRepository.findPostById(postId);
    }

}
