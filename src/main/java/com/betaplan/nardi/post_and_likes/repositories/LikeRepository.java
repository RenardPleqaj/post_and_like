package com.betaplan.nardi.post_and_likes.repositories;

import com.betaplan.nardi.post_and_likes.models.Like;
import org.springframework.data.repository.CrudRepository;



public interface LikeRepository extends CrudRepository<Like,Long> {

    Like findLikeByPost_IdAndUser_Id(Long postId,Long userId);

}
