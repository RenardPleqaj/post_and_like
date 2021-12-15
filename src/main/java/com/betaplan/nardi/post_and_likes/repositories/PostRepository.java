package com.betaplan.nardi.post_and_likes.repositories;

import com.betaplan.nardi.post_and_likes.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findAllByUserId(Long userId);
    Post findPostById(Long postId);
}
