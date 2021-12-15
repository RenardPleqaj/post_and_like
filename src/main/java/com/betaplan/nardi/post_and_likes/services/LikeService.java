package com.betaplan.nardi.post_and_likes.services;

import com.betaplan.nardi.post_and_likes.models.Like;
import com.betaplan.nardi.post_and_likes.models.Post;
import com.betaplan.nardi.post_and_likes.models.User;
import com.betaplan.nardi.post_and_likes.repositories.LikeRepository;
import com.betaplan.nardi.post_and_likes.repositories.PostRepository;
import com.betaplan.nardi.post_and_likes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void createLike(Long postId,Long userId){
        Post post= postRepository.findPostById(postId);
        User user=userRepository.findUserById(userId);
        Like like=new Like(user,post);
        likeRepository.save(like);
    }
    public void deleteLike(Long likeId){
        likeRepository.deleteById(likeId);
    }

    public Like findLike(Long postId,Long userId){
        return likeRepository.findLikeByPost_IdAndUser_Id(postId,userId);
    }


}
