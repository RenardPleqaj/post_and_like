package com.betaplan.nardi.post_and_likes.repositories;


import com.betaplan.nardi.post_and_likes.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findUserById(Long userId);
}